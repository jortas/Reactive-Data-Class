
plugins {
    kotlin("jvm")
    id("maven-publish")
}
group = "com.github.jortas"

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.20")

    implementation ("com.squareup:kotlinpoet:1.12.0")

    implementation("com.squareup:kotlinpoet-metadata:1.12.0")

    implementation ("com.google.devtools.ksp:symbol-processing-api:1.7.20-1.0.6")
    implementation ("com.squareup:kotlinpoet-ksp:1.12.0")
}

