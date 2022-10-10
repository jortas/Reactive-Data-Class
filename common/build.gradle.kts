@file:Suppress("UNUSED_VARIABLE")

plugins {
    id("com.android.library")
    id("kotlinx-serialization")
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("kotlin-parcelize")
    id("com.rickclephas.kmp.nativecoroutines")
    id("com.github.ben-manes.versions").version("0.42.0")
}
// CocoaPods requires the podspec to have a version.
version = "1.1"

android {
    compileSdk = SDK.compile
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = SDK.min
        targetSdk = SDK.target
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

kotlin {
    android()
    val iosTarget: (String, org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget.() -> Unit) -> org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Office Seat Management-KMM"
        homepage = "https://something.com"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../OfficeSeatManagement-iOS/Podfile")

        noPodspec()

        framework {
            isStatic = false
            baseName = "common"
        }

        xcodeConfigurationToNativeBuildType["Test"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(Libs.JetBrains.Coroutines.kotlinxCoroutinesCore) {
                    isForce = true
                }
                implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
                implementation(libs.bundles.ktor.base)
                implementation(Libs.JetBrains.Serialization.core)
                implementation(Libs.JetBrains.Serialization.json)
                implementation(Libs.Multiplatform.multiplatformSettings)
                api(Libs.Multiplatform.mokoParcelize)
                api(Libs.Multiplatform.kermit)
                api(libs.koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                api(libs.koin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.android)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(Libs.Android.Test.junit)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.ios)
            }
        }
    }
}
dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
}
repositories {
    mavenCentral()
}