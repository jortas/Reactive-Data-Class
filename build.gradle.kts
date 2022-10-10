buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath ("com.android.tools.build:gradle:3.0.1")
        classpath ("com.github.dcendents:android-maven-gradle-plugin:2.0")
        classpath ("com.github.dcendents:android-maven-plugin:1.2")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}