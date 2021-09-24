/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.fir.components;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link GenerateNewCompilerTests.kt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("analysis/analysis-api/testData/analysisSession/resolveCall")
@TestDataPath("$PROJECT_ROOT")
public class ResolveCallTestGenerated extends AbstractResolveCallTest {
    @Test
    public void testAllFilesPresentInResolveCall() throws Exception {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/analysis-api/testData/analysisSession/resolveCall"), Pattern.compile("^(.+)\\.kt$"), null, true);
    }

    @Test
    @TestMetadata("annotationEntry.kt")
    public void testAnnotationEntry() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationEntry.kt");
    }

    @Test
    @TestMetadata("annotationInAnnotation_arrayOf.kt")
    public void testAnnotationInAnnotation_arrayOf() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationInAnnotation_arrayOf.kt");
    }

    @Test
    @TestMetadata("annotationInAnnotation_collectionLiteral.kt")
    public void testAnnotationInAnnotation_collectionLiteral() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationInAnnotation_collectionLiteral.kt");
    }

    @Test
    @TestMetadata("annotationInAnnotation_multipleAnnotations_arrayOf.kt")
    public void testAnnotationInAnnotation_multipleAnnotations_arrayOf() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationInAnnotation_multipleAnnotations_arrayOf.kt");
    }

    @Test
    @TestMetadata("annotationInAnnotation_multipleAnnotations_collectionLiteral.kt")
    public void testAnnotationInAnnotation_multipleAnnotations_collectionLiteral() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationInAnnotation_multipleAnnotations_collectionLiteral.kt");
    }

    @Test
    @TestMetadata("annotationInAnnotation_noarg.kt")
    public void testAnnotationInAnnotation_noarg() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationInAnnotation_noarg.kt");
    }

    @Test
    @TestMetadata("annotationInAnnotation_vararg.kt")
    public void testAnnotationInAnnotation_vararg() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationInAnnotation_vararg.kt");
    }

    @Test
    @TestMetadata("annotationOnDelegate.kt")
    public void testAnnotationOnDelegate() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationOnDelegate.kt");
    }

    @Test
    @TestMetadata("annotationOnExpression_asT.kt")
    public void testAnnotationOnExpression_asT() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationOnExpression_asT.kt");
    }

    @Test
    @TestMetadata("annotationOnExpression_destructuring.kt")
    public void testAnnotationOnExpression_destructuring() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationOnExpression_destructuring.kt");
    }

    @Test
    @TestMetadata("annotationOnExpression_if.kt")
    public void testAnnotationOnExpression_if() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationOnExpression_if.kt");
    }

    @Test
    @TestMetadata("annotationOnExpression_whenBranch.kt")
    public void testAnnotationOnExpression_whenBranch() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationOnExpression_whenBranch.kt");
    }

    @Test
    @TestMetadata("annotationOnFile.kt")
    public void testAnnotationOnFile() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationOnFile.kt");
    }

    @Test
    @TestMetadata("annotationOnParameter_param.kt")
    public void testAnnotationOnParameter_param() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationOnParameter_param.kt");
    }

    @Test
    @TestMetadata("annotationOnParameter_parameterProperty.kt")
    public void testAnnotationOnParameter_parameterProperty() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationOnParameter_parameterProperty.kt");
    }

    @Test
    @TestMetadata("annotationOnParameter_reified.kt")
    public void testAnnotationOnParameter_reified() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationOnParameter_reified.kt");
    }

    @Test
    @TestMetadata("annotationOnParameter_setparam.kt")
    public void testAnnotationOnParameter_setparam() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationOnParameter_setparam.kt");
    }

    @Test
    @TestMetadata("annotationOnProperty_field.kt")
    public void testAnnotationOnProperty_field() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationOnProperty_field.kt");
    }

    @Test
    @TestMetadata("annotationOnProperty_get.kt")
    public void testAnnotationOnProperty_get() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationOnProperty_get.kt");
    }

    @Test
    @TestMetadata("annotationOnProperty_property.kt")
    public void testAnnotationOnProperty_property() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationOnProperty_property.kt");
    }

    @Test
    @TestMetadata("annotationOnProperty_set.kt")
    public void testAnnotationOnProperty_set() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationOnProperty_set.kt");
    }

    @Test
    @TestMetadata("annotationOnReceiver.kt")
    public void testAnnotationOnReceiver() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/annotationOnReceiver.kt");
    }

    @Test
    @TestMetadata("arrayOfInAnnotation.kt")
    public void testArrayOfInAnnotation() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/arrayOfInAnnotation.kt");
    }

    @Test
    @TestMetadata("delegatedConstructorCall_super.kt")
    public void testDelegatedConstructorCall_super() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/delegatedConstructorCall_super.kt");
    }

    @Test
    @TestMetadata("delegatedConstructorCall_super_unresolved.kt")
    public void testDelegatedConstructorCall_super_unresolved() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/delegatedConstructorCall_super_unresolved.kt");
    }

    @Test
    @TestMetadata("delegatedConstructorCall_this.kt")
    public void testDelegatedConstructorCall_this() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/delegatedConstructorCall_this.kt");
    }

    @Test
    @TestMetadata("delegatedConstructorCall_this_unresolved.kt")
    public void testDelegatedConstructorCall_this_unresolved() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/delegatedConstructorCall_this_unresolved.kt");
    }

    @Test
    @TestMetadata("enumAsAnnotationValue.kt")
    public void testEnumAsAnnotationValue() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/enumAsAnnotationValue.kt");
    }

    @Test
    @TestMetadata("functionCallInTheSameFile.kt")
    public void testFunctionCallInTheSameFile() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/functionCallInTheSameFile.kt");
    }

    @Test
    @TestMetadata("functionCallWithExtensionReceiverAndTypeArgument.kt")
    public void testFunctionCallWithExtensionReceiverAndTypeArgument() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/functionCallWithExtensionReceiverAndTypeArgument.kt");
    }

    @Test
    @TestMetadata("functionCallWithLambdaArgument.kt")
    public void testFunctionCallWithLambdaArgument() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/functionCallWithLambdaArgument.kt");
    }

    @Test
    @TestMetadata("functionCallWithNamedArgument.kt")
    public void testFunctionCallWithNamedArgument() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/functionCallWithNamedArgument.kt");
    }

    @Test
    @TestMetadata("functionCallWithNonTrailingLambdaArgument.kt")
    public void testFunctionCallWithNonTrailingLambdaArgument() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/functionCallWithNonTrailingLambdaArgument.kt");
    }

    @Test
    @TestMetadata("functionCallWithSpreadArgument.kt")
    public void testFunctionCallWithSpreadArgument() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/functionCallWithSpreadArgument.kt");
    }

    @Test
    @TestMetadata("functionCallWithTypeArgument.kt")
    public void testFunctionCallWithTypeArgument() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/functionCallWithTypeArgument.kt");
    }

    @Test
    @TestMetadata("functionCallWithVarargArgument.kt")
    public void testFunctionCallWithVarargArgument() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/functionCallWithVarargArgument.kt");
    }

    @Test
    @TestMetadata("functionWithReceiverCall.kt")
    public void testFunctionWithReceiverCall() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/functionWithReceiverCall.kt");
    }

    @Test
    @TestMetadata("functionWithReceiverSafeCall.kt")
    public void testFunctionWithReceiverSafeCall() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/functionWithReceiverSafeCall.kt");
    }

    @Test
    @TestMetadata("implicitConstructorDelegationCall.kt")
    public void testImplicitConstructorDelegationCall() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/implicitConstructorDelegationCall.kt");
    }

    @Test
    @TestMetadata("implicitConstuctorCall.kt")
    public void testImplicitConstuctorCall() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/implicitConstuctorCall.kt");
    }

    @Test
    @TestMetadata("implicitJavaConstuctorCall.kt")
    public void testImplicitJavaConstuctorCall() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/implicitJavaConstuctorCall.kt");
    }

    @Test
    @TestMetadata("indexedGet.kt")
    public void testIndexedGet() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/indexedGet.kt");
    }

    @Test
    @TestMetadata("indexedGetWithNotEnoughArgs.kt")
    public void testIndexedGetWithNotEnoughArgs() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/indexedGetWithNotEnoughArgs.kt");
    }

    @Test
    @TestMetadata("indexedGetWithTooManyArgs.kt")
    public void testIndexedGetWithTooManyArgs() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/indexedGetWithTooManyArgs.kt");
    }

    @Test
    @TestMetadata("indexedSet.kt")
    public void testIndexedSet() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/indexedSet.kt");
    }

    @Test
    @TestMetadata("indexedSetWithNotEnoughArgs.kt")
    public void testIndexedSetWithNotEnoughArgs() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/indexedSetWithNotEnoughArgs.kt");
    }

    @Test
    @TestMetadata("indexedSetWithTooManyArgs.kt")
    public void testIndexedSetWithTooManyArgs() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/indexedSetWithTooManyArgs.kt");
    }

    @Test
    @TestMetadata("intArrayOfInAnnotation.kt")
    public void testIntArrayOfInAnnotation() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/intArrayOfInAnnotation.kt");
    }

    @Test
    @TestMetadata("javaFunctionCall.kt")
    public void testJavaFunctionCall() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/javaFunctionCall.kt");
    }

    @Test
    @TestMetadata("javaPropertyGetter.kt")
    public void testJavaPropertyGetter() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/javaPropertyGetter.kt");
    }

    @Test
    @TestMetadata("javaPropertyNestedGetter.kt")
    public void testJavaPropertyNestedGetter() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/javaPropertyNestedGetter.kt");
    }

    @Test
    @TestMetadata("javaPropertySetter.kt")
    public void testJavaPropertySetter() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/javaPropertySetter.kt");
    }

    @Test
    @TestMetadata("javaPropertySetterIncomplete.kt")
    public void testJavaPropertySetterIncomplete() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/javaPropertySetterIncomplete.kt");
    }

    @Test
    @TestMetadata("kotlinPropertyGetter.kt")
    public void testKotlinPropertyGetter() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/kotlinPropertyGetter.kt");
    }

    @Test
    @TestMetadata("kotlinPropertyNestedGetter.kt")
    public void testKotlinPropertyNestedGetter() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/kotlinPropertyNestedGetter.kt");
    }

    @Test
    @TestMetadata("kotlinPropertySetter.kt")
    public void testKotlinPropertySetter() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/kotlinPropertySetter.kt");
    }

    @Test
    @TestMetadata("memberFunctionCallWithTypeArgument.kt")
    public void testMemberFunctionCallWithTypeArgument() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/memberFunctionCallWithTypeArgument.kt");
    }

    @Test
    @TestMetadata("resolveCallInSuperConstructorParam.kt")
    public void testResolveCallInSuperConstructorParam() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/resolveCallInSuperConstructorParam.kt");
    }

    @Test
    @TestMetadata("simpleCallWithNonMatchingArgs.kt")
    public void testSimpleCallWithNonMatchingArgs() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/simpleCallWithNonMatchingArgs.kt");
    }

    @Test
    @TestMetadata("unresolvedSuperReference.kt")
    public void testUnresolvedSuperReference() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/unresolvedSuperReference.kt");
    }

    @Test
    @TestMetadata("variableAsFunction.kt")
    public void testVariableAsFunction() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/variableAsFunction.kt");
    }

    @Test
    @TestMetadata("variableAsFunctionLikeCall.kt")
    public void testVariableAsFunctionLikeCall() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/variableAsFunctionLikeCall.kt");
    }

    @Test
    @TestMetadata("variableWithExtensionInvoke.kt")
    public void testVariableWithExtensionInvoke() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/variableWithExtensionInvoke.kt");
    }

    @Test
    @TestMetadata("variableWithMemberInvoke.kt")
    public void testVariableWithMemberInvoke() throws Exception {
        runTest("analysis/analysis-api/testData/analysisSession/resolveCall/variableWithMemberInvoke.kt");
    }
}
