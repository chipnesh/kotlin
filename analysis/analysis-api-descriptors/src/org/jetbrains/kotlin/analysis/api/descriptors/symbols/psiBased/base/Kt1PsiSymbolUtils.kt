/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.descriptors.symbols.psiBased.base

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.base.Kt1Symbol
import org.jetbrains.kotlin.analysis.api.descriptors.types.Kt1ClassErrorType
import org.jetbrains.kotlin.analysis.api.symbols.markers.KtAnnotationCall
import org.jetbrains.kotlin.analysis.api.symbols.markers.KtSymbolKind
import org.jetbrains.kotlin.analysis.api.symbols.markers.KtTypeAndAnnotations
import org.jetbrains.kotlin.analysis.api.tokens.ValidityToken
import org.jetbrains.kotlin.analysis.api.types.KtType
import org.jetbrains.kotlin.analysis.api.withValidityAssertion
import org.jetbrains.kotlin.cfg.getElementParentDeclaration
import org.jetbrains.kotlin.descriptors.ClassDescriptorWithResolutionScopes
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.descriptors.Visibility
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.hasBody
import org.jetbrains.kotlin.psi.psiUtil.isTopLevelInFileOrScript
import org.jetbrains.kotlin.psi.psiUtil.parentsWithSelf
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.scopes.LexicalScope
import org.jetbrains.kotlin.types.ErrorType
import org.jetbrains.kotlin.types.ErrorUtils

internal val KtDeclaration.ktVisibility: Visibility?
    get() = when {
        hasModifier(KtTokens.PUBLIC_KEYWORD) -> Visibilities.Public
        hasModifier(KtTokens.PROTECTED_KEYWORD) -> Visibilities.Protected
        hasModifier(KtTokens.PRIVATE_KEYWORD) -> Visibilities.Private
        hasModifier(KtTokens.INTERNAL_KEYWORD) -> Visibilities.Internal
        else -> null
    }

internal val KtDeclaration.ktModality: Modality?
    get() = when {
        hasModifier(KtTokens.ABSTRACT_KEYWORD) -> Modality.ABSTRACT
        hasModifier(KtTokens.FINAL_KEYWORD) -> Modality.FINAL
        hasModifier(KtTokens.SEALED_KEYWORD) -> Modality.SEALED
        hasModifier(KtTokens.OPEN_KEYWORD) -> {
            if (this is KtCallableDeclaration && !hasBody()) {
                val parentDeclaration = this.getElementParentDeclaration()
                if (parentDeclaration is KtClass && parentDeclaration.isInterface()) {
                    Modality.ABSTRACT
                } else {
                    Modality.OPEN
                }
            }
            Modality.OPEN
        }
        else -> null
    }

internal val KtElement.ktSymbolKind: KtSymbolKind
    get() = when {
        this is KtPropertyAccessor -> KtSymbolKind.ACCESSOR
        isTopLevelInFileOrScript(this) -> KtSymbolKind.TOP_LEVEL
        this is KtDeclaration && !KtPsiUtil.isLocal(this) -> KtSymbolKind.CLASS_MEMBER
        else -> KtSymbolKind.LOCAL
    }

internal val KtDeclaration.callableId: CallableId?
    get() {
        val selfName = this.name ?: return null
        val containingFile = this.containingKtFile

        var current = this.getElementParentDeclaration()

        val localName = mutableListOf<String>()
        val className = mutableListOf<String>()

        while (current != null) {
            when (current) {
                is KtClassOrObject -> className += current.name ?: return null
                is KtPropertyAccessor -> {} // Filter out property accessors
                is KtCallableDeclaration -> localName += current.name ?: return null
            }

            current = current.getElementParentDeclaration()
        }

        return CallableId(
            packageName = containingFile.packageFqName,
            className = if (className.isNotEmpty()) FqName.fromSegments(className.asReversed()) else null,
            callableName = Name.identifier(selfName),
            pathToLocal = if (localName.isNotEmpty()) FqName.fromSegments(localName.asReversed()) else null
        )
    }

internal fun PsiElement.getResolutionScope(bindingContext: BindingContext): LexicalScope? {
    for (parent in parentsWithSelf) {
        if (parent is KtElement) {
            val scope = bindingContext[BindingContext.LEXICAL_SCOPE, parent]
            if (scope != null) return scope
        }

        if (parent is KtClassBody) {
            val classDescriptor = bindingContext[BindingContext.CLASS, parent.getParent()] as? ClassDescriptorWithResolutionScopes
            if (classDescriptor != null) {
                return classDescriptor.scopeForMemberDeclarationResolution
            }
        }
        if (parent is KtFile) {
            break
        }
    }

    return null
}

internal fun Kt1Symbol.createErrorTypeAndAnnotations(): KtTypeAndAnnotations {
    val errorType = createErrorType()

    return object : KtTypeAndAnnotations() {
        override val type: KtType
            get() = withValidityAssertion { errorType }
        override val annotations: List<KtAnnotationCall>
            get() = withValidityAssertion { emptyList() }
        override val token: ValidityToken
            get() = this@createErrorTypeAndAnnotations.token

    }
}

internal fun Kt1Symbol.createErrorType(): KtType {
    val type = ErrorUtils.createErrorType("Type is unavailable for declaration $psi") as ErrorType
    return Kt1ClassErrorType(type, analysisSession)
}