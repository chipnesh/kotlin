/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.descriptors.utils

import org.jetbrains.kotlin.analysis.api.components.KtTypeRendererOptions
import org.jetbrains.kotlin.analysis.api.descriptors.symbols.descriptorBased.base.maybeLocalClassId
import org.jetbrains.kotlin.builtins.functions.FunctionClassDescriptor
import org.jetbrains.kotlin.builtins.getReceiverTypeFromFunctionType
import org.jetbrains.kotlin.builtins.getReturnTypeFromFunctionType
import org.jetbrains.kotlin.builtins.getValueParameterTypesFromFunctionType
import org.jetbrains.kotlin.descriptors.ClassifierDescriptorWithTypeParameters
import org.jetbrains.kotlin.descriptors.PossiblyInnerType
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.SpecialNames
import org.jetbrains.kotlin.renderer.DescriptorRenderer
import org.jetbrains.kotlin.renderer.render
import org.jetbrains.kotlin.resolve.calls.inference.CapturedType
import org.jetbrains.kotlin.types.*
import org.jetbrains.kotlin.types.checker.NewCapturedType
import org.jetbrains.kotlin.types.checker.NewTypeVariableConstructor
import org.jetbrains.kotlin.types.typeUtil.builtIns

internal class Kt1TypeRenderer(private val options: KtTypeRendererOptions, private val isDebugText: Boolean = false) {
    private companion object {
        const val ERROR_TYPE_TEXT = "ERROR_TYPE"
    }

    fun render(type: KotlinType, consumer: Kt1RendererConsumer) {
        consumer.renderType(type)
    }

    private fun Kt1RendererConsumer.renderType(type: KotlinType) {
        when (val unwrappedType = type.unwrap()) {
            is FlexibleType -> renderFlexibleType(unwrappedType)
            is DefinitelyNotNullType -> renderDefinitelyNotNullType(unwrappedType)
            is ErrorType -> renderErrorType()
            is CapturedType -> renderCapturedType(unwrappedType)
            is NewCapturedType -> renderCapturedType(unwrappedType)
            is AbbreviatedType -> renderType(unwrappedType.abbreviation)
            is SimpleType -> {
                when (val typeConstructor = unwrappedType.constructor) {
                    is NewTypeVariableConstructor -> renderTypeVariableType(typeConstructor)
                    is IntersectionTypeConstructor -> renderIntersectionType(typeConstructor)
                    else -> {
                        val descriptor = unwrappedType.constructor.declarationDescriptor
                        if (!isDebugText && options.renderFunctionType && descriptor is FunctionClassDescriptor) {
                            renderFunctionType(unwrappedType)
                        } else if (descriptor is TypeParameterDescriptor) {
                            renderTypeParameterType(descriptor)
                        } else if (descriptor is ClassifierDescriptorWithTypeParameters) {
                            renderOrdinaryType(unwrappedType)
                        } else {
                            append("ERROR CLASS")
                        }
                    }
                }
            }
        }

        if (type.isMarkedNullable) {
            append("?")
        }
    }

    private fun Kt1RendererConsumer.renderFlexibleType(type: FlexibleType) {
        if (isDebugText) {
            append("ft<")
            renderType(type.lowerBound)
            append(", ")
            renderType(type.upperBound)
            append(">")
            return
        }

        val lowerBoundText = StringBuilder().apply { renderType(type.lowerBound) }.toString()
        val upperBoundText = StringBuilder().apply { renderType(type.upperBound) }.toString()
        append(DescriptorRenderer.COMPACT.renderFlexibleType(lowerBoundText, upperBoundText, type.builtIns))
    }

    private fun Kt1RendererConsumer.renderDefinitelyNotNullType(type: DefinitelyNotNullType) {
        renderType(type.original)
        append(" & Any")
    }

    private fun Kt1RendererConsumer.renderErrorType() {
        append(ERROR_TYPE_TEXT)
    }

    private fun Kt1RendererConsumer.renderCapturedType(type: CapturedType) {
        append("CapturedType(")
        renderTypeProjection(type.typeProjection)
        append(")")
    }

    private fun Kt1RendererConsumer.renderCapturedType(type: NewCapturedType) {
        append("CapturedType(")
        renderTypeProjection(type.constructor.projection)
        append(")")
    }

    private fun Kt1RendererConsumer.renderTypeVariableType(typeConstructor: NewTypeVariableConstructor) {
        val name = typeConstructor.originalTypeParameter?.name ?: SpecialNames.NO_NAME_PROVIDED
        append("TypeVariable(").append(name.asString()).append(")")
    }

    private fun Kt1RendererConsumer.renderIntersectionType(typeConstructor: IntersectionTypeConstructor) {
        if (isDebugText) {
            append("it")
        }
        renderList(typeConstructor.supertypes, separator = " & ", prefix = "(", postfix = ")") { renderType(it) }
    }

    private fun Kt1RendererConsumer.renderFunctionType(type: SimpleType) {
        val receiverType = type.getReceiverTypeFromFunctionType()
        val valueParameters = type.getValueParameterTypesFromFunctionType()
        val returnType = type.getReturnTypeFromFunctionType()

        if (receiverType != null) {
            renderType(receiverType)
            append(".")
        }
        renderList(valueParameters, separator = ", ", prefix = "(", postfix = ")") { renderTypeProjection(it) }
        append(" -> ")
        renderType(returnType)
    }

    private fun Kt1RendererConsumer.renderTypeParameterType(descriptor: TypeParameterDescriptor) {
        append(descriptor.name.render())
    }

    private fun Kt1RendererConsumer.renderOrdinaryType(type: SimpleType) {
        val nestedType = Kt1JvmTypeMapper.getNestedType(type)
        renderTypeSegment(nestedType.root, isRoot = true)
        renderList(nestedType.nested, separator = ".", prefix = ".", postfix = "", renderWhenEmpty = false) { renderTypeSegment(it) }
    }

    private fun Kt1RendererConsumer.renderTypeSegment(typeSegment: PossiblyInnerType, isRoot: Boolean = false) {
        val classifier = typeSegment.classifierDescriptor

        if (isDebugText) {
            append(classifier.maybeLocalClassId.asString())
        } else if (isRoot) {
            val classId = classifier.maybeLocalClassId
            if (!options.shortQualifiedNames && !classId.packageFqName.isRoot) {
                renderFqName(classId.packageFqName)
                append('.')
            }
            renderFqName(classId.relativeClassName)
        } else {
            append(classifier.name.render())
        }

        val arguments = typeSegment.arguments
        renderList(arguments, separator = ", ", prefix = "<", postfix = ">", renderWhenEmpty = false) { renderTypeProjection(it) }
    }

    private fun Kt1RendererConsumer.renderFqName(fqName: FqName) {
        renderList(fqName.pathSegments(), separator = ".") { append(it.render()) }
    }

    private fun Kt1RendererConsumer.renderTypeProjection(projection: TypeProjection) {
        if (projection.isStarProjection) {
            append("*")
        } else {
            when (projection.projectionKind) {
                Variance.INVARIANT -> renderType(projection.type)
                Variance.IN_VARIANCE -> {
                    append("in ")
                    renderType(projection.type)
                }
                Variance.OUT_VARIANCE -> {
                    append("out ")
                    renderType(projection.type)
                }
            }
        }
    }
}