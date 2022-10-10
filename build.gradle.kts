buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.2.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.7.20")
        classpath ("com.vanniktech:gradle-maven-publish-plugin:0.18.0")
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

/**
 * Decides if this version is stable or not.
 */
fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return !isStable
}
