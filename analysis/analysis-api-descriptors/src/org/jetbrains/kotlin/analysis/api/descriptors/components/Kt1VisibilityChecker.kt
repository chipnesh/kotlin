/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.descriptors.components

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.analysis.api.components.KtVisibilityChecker
import org.jetbrains.kotlin.analysis.api.descriptors.Kt1AnalysisSession
import org.jetbrains.kotlin.analysis.api.descriptors.Kt1AnalysisSession.AnalysisMode
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.descriptorBased.base.getSymbolDescriptor
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.psiBased.base.getResolutionScope
import org.jetbrains.kotlin.analysis.api.symbols.KtFileSymbol
import org.jetbrains.kotlin.analysis.api.symbols.markers.KtSymbolWithVisibility
import org.jetbrains.kotlin.analysis.api.tokens.ValidityToken
import org.jetbrains.kotlin.analysis.api.withValidityAssertion
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorWithVisibility
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities
import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.psiUtil.containingClassOrObject
import org.jetbrains.kotlin.psi.psiUtil.parentsWithSelf
import org.jetbrains.kotlin.resolve.descriptorUtil.isExtension
import org.jetbrains.kotlin.resolve.scopes.receivers.ExpressionReceiver
import org.jetbrains.kotlin.resolve.scopes.utils.getImplicitReceiversHierarchy

internal class Kt1VisibilityChecker(override val analysisSession: Kt1AnalysisSession) : KtVisibilityChecker() {
    override val token: ValidityToken
        get() = analysisSession.token

    override fun isVisible(
        candidateSymbol: KtSymbolWithVisibility,
        useSiteFile: KtFileSymbol,
        position: PsiElement,
        receiverExpression: KtExpression?
    ): Boolean = withValidityAssertion {
        if (candidateSymbol.visibility == Visibilities.Public) {
            return true
        }

        val targetDescriptor = getSymbolDescriptor(candidateSymbol) as? DeclarationDescriptorWithVisibility ?: return false

        val useSiteDeclaration = findContainingNonLocalDeclaration(position) ?: return false
        val useSiteSymbol = with(analysisSession) { useSiteDeclaration.getSymbol() }
        val useSiteDescriptor = getSymbolDescriptor(useSiteSymbol) ?: return false

        if (receiverExpression != null && !targetDescriptor.isExtension) {
            val bindingContext = analysisSession.analyze(receiverExpression, AnalysisMode.PARTIAL)
            val receiverType = bindingContext.getType(receiverExpression) ?: return false
            val explicitReceiver = ExpressionReceiver.create(receiverExpression, receiverType, bindingContext)
            return DescriptorVisibilities.isVisible(explicitReceiver, targetDescriptor, useSiteDescriptor)
        } else {
            val bindingContext = analysisSession.analyze(useSiteDeclaration, AnalysisMode.FULL)

            val lexicalScope = position.getResolutionScope(bindingContext)
            if (lexicalScope != null) {
                return lexicalScope.getImplicitReceiversHierarchy()
                    .any { DescriptorVisibilities.isVisible(it.value, targetDescriptor, useSiteDescriptor) }
            }
        }

        return false
    }
}

private fun findContainingNonLocalDeclaration(element: PsiElement): KtCallableDeclaration? {
    return element
        .parentsWithSelf
        .filterIsInstance<KtCallableDeclaration>()
        .firstOrNull { it.isNotFromLocalClass }
}

private val KtCallableDeclaration.isNotFromLocalClass
    get() = this is KtNamedFunction && (isTopLevel || containingClassOrObject?.isLocal == false) ||
            this is KtProperty && (isTopLevel || containingClassOrObject?.isLocal == false)