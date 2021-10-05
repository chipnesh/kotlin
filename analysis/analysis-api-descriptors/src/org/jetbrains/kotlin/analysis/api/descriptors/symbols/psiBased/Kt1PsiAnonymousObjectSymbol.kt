/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.descriptors.symbols.psiBased

import org.jetbrains.kotlin.analysis.api.descriptors.Kt1AnalysisSession
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.descriptorBased.base.toKtTypeAndAnnotations
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.pointers.Kt1NeverRevivingSymbolPointer
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.psiBased.base.Kt1PsiSymbol
import org.jetbrains.kotlin.analysis.api.descriptors.utils.cached
import org.jetbrains.kotlin.analysis.api.symbols.KtAnonymousObjectSymbol
import org.jetbrains.kotlin.analysis.api.symbols.markers.KtTypeAndAnnotations
import org.jetbrains.kotlin.analysis.api.symbols.pointers.KtPsiBasedSymbolPointer
import org.jetbrains.kotlin.analysis.api.symbols.pointers.KtSymbolPointer
import org.jetbrains.kotlin.analysis.api.withValidityAssertion
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.resolve.BindingContext

internal class Kt1PsiAnonymousObjectSymbol(
    override val psi: KtObjectDeclaration,
    override val analysisSession: Kt1AnalysisSession
) : KtAnonymousObjectSymbol(), Kt1PsiSymbol<KtObjectDeclaration, ClassDescriptor> {
    override val descriptor: ClassDescriptor? by cached {
        val bindingContext = analysisSession.analyze(psi, Kt1AnalysisSession.AnalysisMode.PARTIAL)
        bindingContext[BindingContext.CLASS, psi]
    }

    override val superTypes: List<KtTypeAndAnnotations>
        get() = withValidityAssertion {
            descriptor?.typeConstructor?.supertypes?.map { it.toKtTypeAndAnnotations(analysisSession) } ?: emptyList()
        }

    override fun createPointer(): KtSymbolPointer<KtAnonymousObjectSymbol> = withValidityAssertion {
        return KtPsiBasedSymbolPointer.createForSymbolFromSource(this) ?: Kt1NeverRevivingSymbolPointer()
    }
}