/*
 * Copyright 2000-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.resolve.calls.checkers

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.calls.util.isInfixCall
import org.jetbrains.kotlin.resolve.calls.util.isCallableReference
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.calls.model.VariableAsFunctionResolvedCall
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameOrNull
import org.jetbrains.kotlin.serialization.deserialization.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME
import org.jetbrains.kotlin.utils.addToStdlib.safeAs

object FunWithSuspendModifierCallChecker : CallChecker {

    override fun check(resolvedCall: ResolvedCall<*>, reportOn: PsiElement, context: CallCheckerContext) {
        val descriptor = resolvedCall.candidateDescriptor
        val call = resolvedCall.call
        val calleeName = call.referencedName()
        val variableCalleeName = resolvedCall.safeAs<VariableAsFunctionResolvedCall>()?.variableCall?.call?.referencedName()

        if (calleeName != "suspend" && variableCalleeName != "suspend" && descriptor.name.asString() != "suspend") return

        if (descriptor.fqNameOrNull() != KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME && call.hasFormOfSuspendModifierForFun()) {
            context.trace.report(Errors.MODIFIER_FORM_FOR_NON_BUILT_IN_SUSPEND.on(reportOn))
        }
    }

    private fun Call.hasFormOfSuspendModifierForFun() =
        !isCallableReference() && typeArguments.isEmpty() && isInfixWithFunRhs()

    private fun Call.referencedName() =
        calleeExpression?.safeAs<KtSimpleNameExpression>()?.getReferencedName()

    private fun Call.isInfixWithFunRhs() =
        isInfixCall(this)
                && callElement.safeAs<KtBinaryExpression>()?.right is KtNamedFunction
}
