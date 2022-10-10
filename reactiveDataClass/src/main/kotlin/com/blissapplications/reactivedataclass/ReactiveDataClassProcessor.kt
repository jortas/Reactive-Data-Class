package com.blissapplications.reactivedataclass

import com.google.devtools.ksp.getDeclaredFunctions
import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.*
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import java.io.File
import java.io.OutputStream
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class ReactiveDataClass


class ReactiveDataClassProcessor(private val environment: SymbolProcessorEnvironment) :
    SymbolProcessor {
    var roundsProcessed = 0
    override fun process(resolver: Resolver): List<KSAnnotated> {

        //Fix to disable multiroundprocessing, which was making it a bug
        if (roundsProcessed == 1){
            return emptyList()
        }else{
            roundsProcessed++
        }

        environment.logger.warn("Reactive Data Class: Started processing")
        resolver.findAnnotations(ReactiveDataClass::class)
            .filterIsInstance<KSClassDeclaration>()
            .forEach {
                val fileName = "${it.toClassName().simpleName}Reactive"
                val file = createFile(it, fileName)
                if (file != null) {
                    if (it.primaryConstructor == null) {
                        environment.logger.error("Class ${it.simpleName} should be a data class and have a primary constructor")
                    } else {
                        processAnnotation(it, file, fileName)
                    }
                }
            }

        return resolver.findAnnotations(ReactiveDataClass::class).toList()
    }

    private fun createFile(
        it: KSClassDeclaration,
        fileName: String
    ): OutputStream? {
        return try {
            val result = environment.codeGenerator.createNewFile(
                Dependencies(false),
                packageName = it.toClassName().packageName,
                fileName
            )
            environment.logger.info("Reactive Data Class: $fileName file created with success")
            result
        } catch (e: FileAlreadyExistsException) {
            environment.logger.warn("Reactive Data Class: $fileName is already created")
            null
        }
    }

    private fun Resolver.findAnnotations(
        kClass: KClass<*>,
    ) = getSymbolsWithAnnotation(kClass.qualifiedName.toString())

    private fun processAnnotation(
        classDeclaration: KSClassDeclaration,
        fileOutput: OutputStream,
        fileName: String
    ) {
        val className = classDeclaration.toClassName()
        val pack = className.packageName

        val fileBuilder = FileSpec.builder(pack, fileName)

        val classBuilder = generateClassReactive(fileName, classDeclaration)

        val file = fileBuilder.addType(classBuilder.build()).build()

        fileOutput.write(file.toString().toByteArray())
        fileOutput.close()
    }

    private fun generateClassReactive(
        fileName: String,
        dataClass: KSClassDeclaration,
    ): TypeSpec.Builder {
        val classBuilder = TypeSpec.classBuilder(fileName)
        val primaryConstructorParameters = dataClass.primaryConstructor!!.parameters.map { it }

        val primaryConstructor = parseConstructor(dataClass, classBuilder)
        parseFunctions(dataClass, classBuilder)
        parseProperties(dataClass, classBuilder)

        classBuilder.primaryConstructor(primaryConstructor.build())

        classBuilder.addStateFlow(
            className = dataClass.toClassName(),
            classType = dataClass.asStarProjectedType().toClassName(),
            classProperties = primaryConstructorParameters.map { it.name!! }.toList()
        )
        classBuilder.addStateEmittedFlow(
            className = dataClass.toClassName(),
            classType = dataClass.asStarProjectedType().toClassName(),
        )
        return classBuilder
    }

    private fun parseProperties(
        dataClass: KSClassDeclaration,
        classBuilder: TypeSpec.Builder
    ) {
        val primaryConstructorParameters = dataClass.primaryConstructor!!.parameters.map { it }
        val contructorParametersNames =
            primaryConstructorParameters.map { it.name!!.getShortName() }

        dataClass.getDeclaredProperties()
            .filterNot { it.simpleName.getShortName() in contructorParametersNames }
            .forEach { classBuilder.addPropertyGetters(it) }
    }

    private fun parseFunctions(
        dataClass: KSClassDeclaration,
        classBuilder: TypeSpec.Builder
    ) {
        dataClass.getDeclaredFunctions()
            .filterNot { it.simpleName.getShortName() == "<init>" }
            .forEach { classBuilder.addFunction(it) }
    }

    private fun parseConstructor(
        dataClass: KSClassDeclaration,
        classBuilder: TypeSpec.Builder
    ): FunSpec.Builder {
        val primaryConstructorParameters = dataClass.primaryConstructor!!.parameters.map { it }
        val primaryConstructor = FunSpec.constructorBuilder()

        primaryConstructor.addParameter(
            "initialState",
            dataClass.asStarProjectedType().toClassName()
        )

        primaryConstructorParameters.forEach {
            classBuilder.addProperty(
                it.name!!.getShortName(), it.type
            )
        }

        primaryConstructorParameters.forEach {
            classBuilder.addPropertySetter(
                it.name!!.getShortName(), it.type
            )
        }

        return primaryConstructor
    }

    private fun TypeSpec.Builder.addPropertyGetters(
        property: KSPropertyDeclaration
    ) {
        addProperty(
            PropertySpec
                .builder(property.simpleName.getShortName(), property.type.toTypeName())
                .getter(
                    FunSpec.getterBuilder()
                        .addStatement("return savedState.value.${property.simpleName.getShortName()}")
                        .build()
                )
                .build()
        )
    }
}

private fun TypeSpec.Builder.addPropertySetter(
    name: String,
    type: KSTypeReference
) {
    val builder = FunSpec.builder(
        "set${name.replaceFirstChar { it.uppercase() }}"
    )

    builder.addParameter(
        ParameterSpec.builder(
            "${name}New",
            type.toTypeName()
        ).build()
    )

    builder.addParameter(
        ParameterSpec.builder(
            "suppressEmission",
            Boolean::class
        )
            .defaultValue(CodeBlock.of("false"))
            .build()
    )

    builder.addStatement("_$name = ${name}New")
        .addStatement("updateState()")
        .addStatement("if (suppressEmission) {return }")
        .addStatement("emit()")

    addFunction(builder.build())
}

private fun TypeSpec.Builder.addFunction(
    function: KSFunctionDeclaration
) {
    val builder = FunSpec.builder(
        function.simpleName.getShortName()
    )
    val allStateParameters =
        if (function.parameters.isEmpty()) {
            ""
        } else {
            function.parameters.map { "${it.name!!.getShortName()} = ${it.name!!.getShortName()}" }
                .reduce { acc, name -> "$acc, $name" }
        }

    function.parameters.forEach { parameter ->
        builder.addParameter(
            ParameterSpec.builder(
                parameter.name!!.getShortName(),
                parameter.type.toTypeName()
            ).build()
        )
    }
    builder.addStatement("return savedState.value.${function.simpleName.getShortName()}($allStateParameters)")

    addFunction(builder.build())
}


private fun TypeSpec.Builder.addProperty(
    name: String,
    type: KSTypeReference
) {
    addProperty(
        PropertySpec.builder(
            "_$name",
            type.toTypeName()
        ).mutable()
            .initializer("initialState.${name}")
            .addModifiers(KModifier.PRIVATE)
            .build()
    )

    addProperty(
        PropertySpec.builder(
            name,
            type.toTypeName()
        ).mutable()
            .setter(
                FunSpec.setterBuilder()
                    .addParameter("value", type.toTypeName())
                    .addStatement("set${name.replaceFirstChar { it.uppercase() }}(value, false)")
                    .build()
            ).getter(
                FunSpec.getterBuilder()
                    .addStatement("return _$name")
                    .build()
            )
            .build()
    )
}

private fun TypeSpec.Builder.addStateFlow(
    className: ClassName,
    classType: TypeName,
    classProperties: List<KSName>
) {
    val mutableFlowType = ClassName("kotlinx.coroutines.flow", "MutableStateFlow")
    val mutableFlowTypeParameterized = mutableFlowType.parameterizedBy(classType)

    val allStateParameters =
        classProperties.map { "${it.getShortName()} = _${it.getShortName()}" }
            .reduce { acc, name -> "$acc, $name" }

    val stateCreationInvocations = "MutableStateFlow(initialState)"

    val stateProperty = PropertySpec.builder(
        "savedState",
        mutableFlowTypeParameterized
    ).initializer(stateCreationInvocations)
        .addModifiers(KModifier.PRIVATE)
        .build()

    val emitFunction = FunSpec
        .builder("updateState")
        .addModifiers(KModifier.PRIVATE)
        .addStatement("savedState.value = ${className.simpleName}($allStateParameters)")
        .build()

    addProperty(stateProperty)
    addFunction(emitFunction)
}


private fun TypeSpec.Builder.addStateEmittedFlow(
    className: ClassName,
    classType: TypeName,
) {
    val mutableFlowType = ClassName("kotlinx.coroutines.flow", "MutableStateFlow")
    val mutableFlowTypeParameterized = mutableFlowType.parameterizedBy(classType)

    val flowType = ClassName("kotlinx.coroutines.flow", "StateFlow")
    val flowTypeParameterized = flowType.parameterizedBy(classType)

    val stateCreationInvocations = "MutableStateFlow(savedState.value)"

    val stateProperty = PropertySpec.builder(
        "state",
        mutableFlowTypeParameterized
    ).initializer(stateCreationInvocations)
        .addModifiers(KModifier.PRIVATE)
        .build()


    val flow = PropertySpec.builder(
        "flow",
        flowTypeParameterized
    ).getter(
        FunSpec.getterBuilder()
            .addStatement("return state")
            .build()
    )
        .build()

    val emitFunction = FunSpec
        .builder("emit")
        .addStatement("state.value = savedState.value")
        .build()

    addProperty(stateProperty)
    addProperty(flow)
    addFunction(emitFunction)
}
