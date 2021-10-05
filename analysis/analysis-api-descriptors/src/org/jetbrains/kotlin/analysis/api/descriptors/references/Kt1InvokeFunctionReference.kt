/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.descriptors.references

import org.jetbrains.kotlin.analysis.api.KtAnalysisSession
import org.jetbrains.kotlin.analysis.api.descriptors.Kt1AnalysisSession
import org.jetbrains.kotlin.analysis.api.descriptors.references.base.Kt1Reference
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.descriptorBased.base.toKtCallableSymbol
import org.jetbrains.kotlin.analysis.api.symbols.KtSymbol
import org.jetbrains.kotlin.idea.references.KtInvokeFunctionReference
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.resolve.calls.util.getResolvedCall

internal class Kt1InvokeFunctionReference(expression: KtCallExpression) : KtInvokeFunctionReference(expression), Kt1Reference {
    override fun KtAnalysisSession.resolveToSymbols(): Collection<KtSymbol> {
        require(this is Kt1AnalysisSession)

        val bindingContext = analyze(expression, Kt1AnalysisSession.AnalysisMode.PARTIAL)
        val descriptor = expression.getResolvedCall(bindingContext)?.resultingDescriptor
        return listOfNotNull(descriptor?.toKtCallableSymbol(this))
    }

    override fun doRenameImplicitConventionalCall(newName: String?): KtExpression {
        TODO("Not yet implemented")
    }
}