/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.descriptors.symbols.psiBased

import org.jetbrains.kotlin.analysis.api.descriptors.Kt1AnalysisSession
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.descriptorBased.base.toKtTypeAndAnnotations
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.pointers.Kt1NeverRevivingSymbolPointer
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.psiBased.base.Kt1PsiSymbol
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.psiBased.base.createErrorTypeAndAnnotations
import org.jetbrains.kotlin.analysis.api.descriptors.utils.cached
import org.jetbrains.kotlin.analysis.api.symbols.KtAnonymousFunctionSymbol
import org.jetbrains.kotlin.analysis.api.symbols.KtValueParameterSymbol
import org.jetbrains.kotlin.analysis.api.symbols.markers.KtTypeAndAnnotations
import org.jetbrains.kotlin.analysis.api.symbols.pointers.KtPsiBasedSymbolPointer
import org.jetbrains.kotlin.analysis.api.symbols.pointers.KtSymbolPointer
import org.jetbrains.kotlin.analysis.api.withValidityAssertion
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.psi.KtFunctionLiteral
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import org.jetbrains.kotlin.resolve.BindingContext

internal class Kt1PsiLiteralAnonymousFunctionSymbol(
    override val psi: KtFunctionLiteral,
    override val analysisSession: Kt1AnalysisSession
) : KtAnonymousFunctionSymbol(), Kt1PsiSymbol<KtFunctionLiteral, FunctionDescriptor> {
    override val descriptor: FunctionDescriptor? by cached {
        val bindingContext = analysisSession.analyze(psi)
        bindingContext[BindingContext.FUNCTION, psi]
    }

    override val valueParameters: List<KtValueParameterSymbol>
        get() = withValidityAssertion { psi.valueParameters.map { Kt1PsiValueParameterSymbol(it, analysisSession) } }

    override val hasStableParameterNames: Boolean
        get() = withValidityAssertion { true }

    override val annotatedType: KtTypeAndAnnotations
        get() = withValidityAssertion {
            descriptor?.returnType?.toKtTypeAndAnnotations(analysisSession) ?: createErrorTypeAndAnnotations()
        }

    override val receiverType: KtTypeAndAnnotations?
        get() = withValidityAssertion {
            descriptor?.extensionReceiverParameter?.type?.toKtTypeAndAnnotations(analysisSession) ?: createErrorTypeAndAnnotations()
        }

    override val isExtension: Boolean
        get() = withValidityAssertion { psi.isExtensionDeclaration() }

    override fun createPointer(): KtSymbolPointer<KtAnonymousFunctionSymbol> = withValidityAssertion {
        return KtPsiBasedSymbolPointer.createForSymbolFromSource(this) ?: Kt1NeverRevivingSymbolPointer()
    }
}