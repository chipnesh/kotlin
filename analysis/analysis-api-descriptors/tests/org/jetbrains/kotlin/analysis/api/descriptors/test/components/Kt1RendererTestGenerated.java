/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.descriptors.test.components;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link GenerateNewCompilerTests.kt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("analysis/analysis-api/testData/components/declarationRenderer")
@TestDataPath("$PROJECT_ROOT")
public class Kt1RendererTestGenerated extends AbstractKt1RendererTest {
    @Test
    public void testAllFilesPresentInDeclarationRenderer() throws Exception {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/analysis-api/testData/components/declarationRenderer"), Pattern.compile("^(.+)\\.kt$"), null, true);
    }

    @Test
    @TestMetadata("annotation.kt")
    public void testAnnotation() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/annotation.kt");
    }

    @Test
    @TestMetadata("complexTypes.kt")
    public void testComplexTypes() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/complexTypes.kt");
    }

    @Test
    @TestMetadata("constructorInObject.kt")
    public void testConstructorInObject() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/constructorInObject.kt");
    }

    @Test
    @TestMetadata("constructorOfAnonymousObject.kt")
    public void testConstructorOfAnonymousObject() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/constructorOfAnonymousObject.kt");
    }

    @Test
    @TestMetadata("delegates.kt")
    public void testDelegates() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/delegates.kt");
    }

    @Test
    @TestMetadata("derivedClass.kt")
    public void testDerivedClass() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/derivedClass.kt");
    }

    @Test
    @TestMetadata("emptyAnonymousObject.kt")
    public void testEmptyAnonymousObject() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/emptyAnonymousObject.kt");
    }

    @Test
    @TestMetadata("enums.kt")
    public void testEnums() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/enums.kt");
    }

    @Test
    @TestMetadata("enums2.kt")
    public void testEnums2() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/enums2.kt");
    }

    @Test
    @TestMetadata("expectActual.kt")
    public void testExpectActual() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/expectActual.kt");
    }

    @Test
    @TestMetadata("F.kt")
    public void testF() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/F.kt");
    }

    @Test
    @TestMetadata("functionTypes.kt")
    public void testFunctionTypes() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/functionTypes.kt");
    }

    @Test
    @TestMetadata("genericFunctions.kt")
    public void testGenericFunctions() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/genericFunctions.kt");
    }

    @Test
    @TestMetadata("genericProperty.kt")
    public void testGenericProperty() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/genericProperty.kt");
    }

    @Test
    @TestMetadata("intersectionType.kt")
    public void testIntersectionType() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/intersectionType.kt");
    }

    @Test
    @TestMetadata("nestedClass.kt")
    public void testNestedClass() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/nestedClass.kt");
    }

    @Test
    @TestMetadata("NestedOfAliasedType.kt")
    public void testNestedOfAliasedType() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/NestedOfAliasedType.kt");
    }

    @Test
    @TestMetadata("NestedSuperType.kt")
    public void testNestedSuperType() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/NestedSuperType.kt");
    }

    @Test
    @TestMetadata("noPrimaryConstructor.kt")
    public void testNoPrimaryConstructor() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/noPrimaryConstructor.kt");
    }

    @Test
    @TestMetadata("simpleClass.kt")
    public void testSimpleClass() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/simpleClass.kt");
    }

    @Test
    @TestMetadata("simpleFun.kt")
    public void testSimpleFun() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/simpleFun.kt");
    }

    @Test
    @TestMetadata("simpleTypeAlias.kt")
    public void testSimpleTypeAlias() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/simpleTypeAlias.kt");
    }

    @Test
    @TestMetadata("typeAliasWithGeneric.kt")
    public void testTypeAliasWithGeneric() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/typeAliasWithGeneric.kt");
    }

    @Test
    @TestMetadata("typeParameterVsNested.kt")
    public void testTypeParameterVsNested() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/typeParameterVsNested.kt");
    }

    @Test
    @TestMetadata("typeParameters.kt")
    public void testTypeParameters() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/typeParameters.kt");
    }

    @Test
    @TestMetadata("vararg.kt")
    public void testVararg() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/vararg.kt");
    }

    @Test
    @TestMetadata("where.kt")
    public void testWhere() throws Exception {
        runTest("analysis/analysis-api/testData/components/declarationRenderer/where.kt");
    }
}
