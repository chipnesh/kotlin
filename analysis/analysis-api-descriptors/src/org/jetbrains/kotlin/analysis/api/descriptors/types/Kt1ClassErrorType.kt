/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.descriptors.types

import org.jetbrains.kotlin.analysis.api.descriptors.Kt1AnalysisSession
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.descriptorBased.base.ktNullability
import org.jetbrains.kotlin.analysis.api.descriptors.types.base.Kt1Type
import org.jetbrains.kotlin.analysis.api.descriptors.types.base.asStringForDebugging
import org.jetbrains.kotlin.analysis.api.symbols.KtClassLikeSymbol
import org.jetbrains.kotlin.analysis.api.types.KtClassErrorType
import org.jetbrains.kotlin.analysis.api.types.KtTypeNullability
import org.jetbrains.kotlin.analysis.api.withValidityAssertion
import org.jetbrains.kotlin.types.ErrorType

internal class Kt1ClassErrorType(
    override val type: ErrorType,
    override val analysisSession: Kt1AnalysisSession
) : KtClassErrorType(), Kt1Type {
    override fun asStringForDebugging(): String = withValidityAssertion { type.asStringForDebugging() }

    override val error: String
        get() = withValidityAssertion { "Type \"${type.presentableName}\" is unresolved" }

    override val candidateClassSymbols: Collection<KtClassLikeSymbol>
        get() = withValidityAssertion { emptyList() }

    override val nullability: KtTypeNullability
        get() = withValidityAssertion { type.ktNullability }
}