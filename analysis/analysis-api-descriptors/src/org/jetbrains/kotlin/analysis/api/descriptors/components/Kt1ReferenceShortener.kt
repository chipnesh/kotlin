/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.descriptors.components

import com.intellij.openapi.util.TextRange
import org.jetbrains.kotlin.analysis.api.components.KtReferenceShortener
import org.jetbrains.kotlin.analysis.api.components.ShortenCommand
import org.jetbrains.kotlin.analysis.api.components.ShortenOption
import org.jetbrains.kotlin.analysis.api.descriptors.Kt1AnalysisSession
import org.jetbrains.kotlin.analysis.api.symbols.KtCallableSymbol
import org.jetbrains.kotlin.analysis.api.symbols.KtClassLikeSymbol
import org.jetbrains.kotlin.analysis.api.tokens.ValidityToken
import org.jetbrains.kotlin.psi.KtFile

internal class Kt1ReferenceShortener(override val analysisSession: Kt1AnalysisSession) : KtReferenceShortener() {
    override val token: ValidityToken
        get() = analysisSession.token

    override fun collectShortenings(
        file: KtFile,
        selection: TextRange,
        classShortenOption: (KtClassLikeSymbol) -> ShortenOption,
        callableShortenOption: (KtCallableSymbol) -> ShortenOption
    ): ShortenCommand {
        // Compiler implementation does nothing.
        // Descriptor-based shortening is implemented on the IDE plugin side.
        return object : ShortenCommand {
            override val isEmpty: Boolean
                get() = true

            override fun invokeShortening() {}
        }
    }
}