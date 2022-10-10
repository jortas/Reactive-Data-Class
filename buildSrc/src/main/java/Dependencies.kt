@file:Suppress("unused")

object SDK {
    const val min = 26
    @Suppress("MemberVisibilityCanBePrivate")
    const val compile = 33
    const val target = compile
}

@Deprecated("Change everything to settings.gradle.kts")
object Versions {

    object Jetbrains {
        const val kotlinxSerialization = "1.2.2"
        const val kotlinxCoroutines = "1.6.0-native-mt"
    }

    object Multiplatform {
        const val kermit = "1.1.3"
        const val koin = "3.1.4"
        const val multiplatformSettings = "0.8.1"
        const val mokoParcelize = "0.8.0"
        const val sqlDelight = "1.5.3"
        const val kmpNativeCoroutinesVersion = "0.11.1-new-mm"
    }

    object Android {
        const val chucker = "3.5.2"
        const val resourcesProvider = "1.3.2"

        object Core {
            const val core = "1.5.3"
            const val lifecycleKtx = "2.4.0"
            const val lifecycle = "2.2.0"
            const val material = "1.4.0"
            const val appcompat = "1.4.1"
            const val securityCrypto = "1.1.0-alpha03"
        }

        object Services {
            const val plugin = "4.3.10"
            const val auth = "20.1.0"
            const val firebaseAppDistribution = "2.1.3"
        }

        object Compose {
            const val compiler = "1.1.0"
            const val accompanist = "0.22.1-rc"
            const val paging = "1.0.0-alpha12"
            const val constraintLayout = "1.0.0"
            const val navigation = "2.4.0-rc01"
            const val coil = "2.1.0"
        }

        object Testing {
            const val junit = "4.13.2"
            const val testRunner = "1.4.0"
            const val androidxCore = "1.4.0"
            const val robolectric = "4.7.3"
        }
    }

    object Gradle {
        const val dependencyUpdate = "0.42.0"
        const val easyLauncher = "5.0.0"
        const val androidTools = "7.1.1"
        const val firebaseAppDistribution = "3.0.0"
        const val googleServices = "4.3.10"
    }
}

@Deprecated("Change everything to settings.gradle.kts")
object Libs {

    object Multiplatform {
        const val multiplatformSettings = "com.russhwolf:multiplatform-settings:${Versions.Multiplatform.multiplatformSettings}"
        const val mokoParcelize = "dev.icerock.moko:parcelize:${Versions.Multiplatform.mokoParcelize}"
        const val kermit = "co.touchlab:kermit:${Versions.Multiplatform.kermit}"
    }

    object Android {
        const val chucker = "com.github.chuckerteam.chucker:library:${Versions.Android.chucker}"
        const val resourcesProvider = "com.github.guilhe:resources-provider-ktx:${Versions.Android.resourcesProvider}"

        object Core {
            const val material = "com.google.android.material:material:${Versions.Android.Core.material}"
            const val appcompat = "androidx.appcompat:appcompat:${Versions.Android.Core.appcompat}"
            const val coreKtx = "androidx.core:core-ktx:${Versions.Android.Core.core}"
            const val securityCrypto = "androidx.security:security-crypto:${Versions.Android.Core.securityCrypto}"
        }

        object Lifecycle {
            const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.Core.lifecycleKtx}"
            const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.Core.lifecycleKtx}"
            const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata:${Versions.Android.Core.lifecycle}"
            const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.Android.Core.lifecycle}"
        }

        object Services {
            const val auth = "com.google.android.gms:play-services-auth:${Versions.Android.Services.auth}"
        }

        object Compose {
            const val coil = "io.coil-kt:coil-compose:${Versions.Android.Compose.coil}"
        }

        object Test {
            const val junit = "junit:junit:${Versions.Android.Testing.junit}"
            const val runner = "androidx.test:runner:${Versions.Android.Testing.testRunner}"
            const val core = "androidx.test:core:${Versions.Android.Testing.androidxCore}"
            const val rules = "androidx.test:rules:${Versions.Android.Testing.androidxCore}"
            const val robolectric = "org.robolectric:robolectric:${Versions.Android.Testing.robolectric}"
        }
    }

    object JetBrains {
        object Coroutines {
            const val kotlinxCoroutinesCoreNoVersion = "org.jetbrains.kotlinx:kotlinx-coroutines-core"
            const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Jetbrains.kotlinxCoroutines}"
            const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Jetbrains.kotlinxCoroutines}"
        }

        object Serialization {
            const val core = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.Jetbrains.kotlinxSerialization}"
            const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Jetbrains.kotlinxSerialization}"
        }
    }

    object Gradle {
        const val dependencyUpdate = "com.github.ben-manes.versions"
        const val androidTools = "com.android.tools.build:gradle:${Versions.Gradle.androidTools}"
        const val googleServices = "com.google.gms:google-services:${Versions.Gradle.googleServices}"
        const val firebaseAppDistribution = "com.google.firebase:firebase-appdistribution-gradle:${Versions.Gradle.firebaseAppDistribution}"
    }

    object SqlDelight {
        val runtime = "com.squareup.sqldelight:runtime:${Versions.Multiplatform.sqlDelight}"
        val coroutineExtensions = "com.squareup.sqldelight:coroutines-extensions:${Versions.Multiplatform.sqlDelight}"
        val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.Multiplatform.sqlDelight}"

        val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.Multiplatform.sqlDelight}"
        val nativeDriverMacos = "com.squareup.sqldelight:native-driver-macosx64:${Versions.Multiplatform.sqlDelight}"
        val sqlliteDriver = "com.squareup.sqldelight:sqlite-driver:${Versions.Multiplatform.sqlDelight}"
    }
}