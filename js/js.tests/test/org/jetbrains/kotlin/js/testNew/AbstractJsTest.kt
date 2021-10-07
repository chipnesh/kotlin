/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.js.testNew

import org.jetbrains.kotlin.js.testNew.converters.ClassicJsBackendFacade
import org.jetbrains.kotlin.js.testNew.handlers.JsAstHandler
import org.jetbrains.kotlin.js.testNew.handlers.JsPrefixPostfixHandler
import org.jetbrains.kotlin.js.testNew.handlers.JsSourceMapHandler
import org.jetbrains.kotlin.js.testNew.handlers.JsWrongModuleHandler
import org.jetbrains.kotlin.test.Constructor
import org.jetbrains.kotlin.test.TargetBackend
import org.jetbrains.kotlin.test.backend.classic.ClassicBackendInput
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.builders.configureJsArtifactsHandlersStep
import org.jetbrains.kotlin.test.directives.JsEnvironmentConfigurationDirectives
import org.jetbrains.kotlin.test.frontend.classic.ClassicFrontend2ClassicBackendConverter
import org.jetbrains.kotlin.test.frontend.classic.ClassicFrontendFacade
import org.jetbrains.kotlin.test.frontend.classic.ClassicFrontendOutputArtifact
import org.jetbrains.kotlin.test.model.*
import org.jetbrains.kotlin.test.services.configuration.JsEnvironmentConfigurator

abstract class AbstractJsTest(
    pathToTestDir: String,
    testGroupOutputDirPrefix: String,
) : AbstractJsBlackBoxCodegenTestBase<ClassicFrontendOutputArtifact, ClassicBackendInput>(
    FrontendKinds.ClassicFrontend, TargetBackend.JS, pathToTestDir, testGroupOutputDirPrefix
) {
    override val frontendFacade: Constructor<FrontendFacade<ClassicFrontendOutputArtifact>>
        get() = ::ClassicFrontendFacade

    override val frontendToBackendConverter: Constructor<Frontend2BackendConverter<ClassicFrontendOutputArtifact, ClassicBackendInput>>
        get() = ::ClassicFrontend2ClassicBackendConverter

    override val backendFacade: Constructor<BackendFacade<ClassicBackendInput, BinaryArtifacts.Js>>
        get() = ::ClassicJsBackendFacade

    override fun configure(builder: TestConfigurationBuilder) {
        super.configure(builder)
        with(builder) {
            configureJsArtifactsHandlersStep {
                useHandlers(
                    ::JsAstHandler,
                    ::JsSourceMapHandler,
                )
            }
        }
    }
}

open class AbstractBoxJsTest : AbstractJsTest(pathToTestDir = "${JsEnvironmentConfigurator.TEST_DATA_DIR_PATH}/box/", testGroupOutputDirPrefix = "box/") {
    override fun configure(builder: TestConfigurationBuilder) {
        super.configure(builder)
        builder.defaultDirectives {
            +JsEnvironmentConfigurationDirectives.RUN_MINIFIER_BY_DEFAULT
        }
    }
}

open class AbstractJsCodegenBoxTest : AbstractJsTest(
    pathToTestDir = "compiler/testData/codegen/box/",
    testGroupOutputDirPrefix = "codegen/box/"
)

open class AbstractJsCodegenInlineTest : AbstractJsTest(
    pathToTestDir = "compiler/testData/codegen/boxInline",
    testGroupOutputDirPrefix = "codegen/boxInline"
)

open class AbstractIrCodegenWasmJsInteropJsTest : AbstractJsTest(
    pathToTestDir = "compiler/testData/codegen/boxWasmJsInterop",
    testGroupOutputDirPrefix = "codegen/wasmJsInteropWasm"
)

open class AbstractJsLegacyPrimitiveArraysBoxTest : AbstractJsTest(
    pathToTestDir = "compiler/testData/codegen/box/arrays/",
    testGroupOutputDirPrefix = "codegen/box/arrays-legacy-primitivearrays/"
) {
    override fun configure(builder: TestConfigurationBuilder) {
        super.configure(builder)
        builder.defaultDirectives {
            -JsEnvironmentConfigurationDirectives.TYPED_ARRAYS
        }
    }
}

open class AbstractSourceMapGenerationSmokeTest : AbstractJsTest(
    pathToTestDir = "${JsEnvironmentConfigurator.TEST_DATA_DIR_PATH}/sourcemap/",
    testGroupOutputDirPrefix = "sourcemap/"
) {
    override fun configure(builder: TestConfigurationBuilder) {
        super.configure(builder)
        builder.defaultDirectives {
            +JsEnvironmentConfigurationDirectives.GENERATE_SOURCE_MAP
            -JsEnvironmentConfigurationDirectives.GENERATE_NODE_JS_RUNNER
        }
    }
}

open class AbstractOutputPrefixPostfixTest : AbstractJsTest(
    pathToTestDir = "${JsEnvironmentConfigurator.TEST_DATA_DIR_PATH}/outputPrefixPostfix/",
    testGroupOutputDirPrefix = "outputPrefixPostfix/"
) {
    override fun configure(builder: TestConfigurationBuilder) {
        super.configure(builder)
        builder.defaultDirectives {
            -JsEnvironmentConfigurationDirectives.GENERATE_NODE_JS_RUNNER
        }
        builder.configureJsArtifactsHandlersStep {
            useHandlers(
                ::JsPrefixPostfixHandler
            )
        }
    }
}

open class AbstractMultiModuleOrderTest : AbstractJsTest(
    pathToTestDir = "${JsEnvironmentConfigurator.TEST_DATA_DIR_PATH}/multiModuleOrder/",
    testGroupOutputDirPrefix = "multiModuleOrder/"
) {
    override fun configure(builder: TestConfigurationBuilder) {
        super.configure(builder)
        builder.configureJsArtifactsHandlersStep {
            useHandlers(
                ::JsWrongModuleHandler
            )
        }
    }
}

open class AbstractLegacyJsTypeScriptExportTest : AbstractJsTest(
    pathToTestDir = "${JsEnvironmentConfigurator.TEST_DATA_DIR_PATH}/typescript-export/",
    testGroupOutputDirPrefix = "legacy-typescript-export/"
)
