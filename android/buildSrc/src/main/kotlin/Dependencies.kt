@file:Suppress("unused")

import org.gradle.api.JavaVersion

object Config {
    const val appId = "com.evartem.jobajob"
    const val compileSdk = 29
    const val targetSdk = 29
    const val minSdk = 21
    const val versionCode = 1
    const val versionName = "1.0.0"
    val javaVersion = JavaVersion.VERSION_1_8
}

object Versions {
    const val kotlin = "1.3.50"
    const val androidX = "1.1.0"
    const val lifecycle = "2.1.0"
    const val dagger = "2.24"
    const val rxJava = "2.2.12"
    const val rxKotlin = "2.4.0"
    const val retrofit = "2.6.2"
    const val okhttp = "4.2.1"
}

object Libraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    // AndroidX
    const val xAppCompat = "androidx.appcompat:appcompat:${Versions.androidX}"
    const val xCoreKtx = "androidx.core:core-ktx:${Versions.androidX}"
    const val xLifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val xLifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val xConstraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    const val xPreference = "androidx.preference:preference:1.1.0"
    const val xMaterial = "com.google.android.material:material:1.0.0"

    // Dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    // Rx
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val rxPreferences = "com.f2prateek.rx.preferences2:rx-preferences:2.0.0"

    // Network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitRx = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val gson = "com.google.code.gson:gson:2.8.6"

    // Misc
    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.0-beta-3"
    const val cicerone = "ru.terrakok.cicerone:cicerone:5.0.0"

    // Tests
    const val testJunit = "junit:junit:4.12"
    const val testRunner = "androidx.test.ext:junit:1.1.1"
    const val testEspresso = "androidx.test.espresso:espresso-core:3.2.0"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"

    // Gradle
    const val gradleAndroid = "com.android.tools.build:gradle:3.5.0"
    const val gradleKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradleVersions = "com.github.ben-manes:gradle-versions-plugin:0.25.0"
}

object Modules {
    const val libraryCore = ":libraries:core"
    const val libraryUtils = ":libraries:utils"
    const val libraryNetwork = ":libraries:network"
    const val libraryUiComponents = ":libraries:ui-components"

    const val libraryPreferencesApi = ":libraries:preferences:preferences-api"
    const val libraryPreferencesImpl = ":libraries:preferences:preferences-impl"

    const val featureLoginApi = ":features:login:login-api"
    const val featureLoginImpl = ":features:login:login-impl"

    const val featureDashboardApi = ":features:dashboard:dashboard-api"
    const val featureDashboardImpl = ":features:dashboard:dashboard-impl"

    const val featureFavoritesApi = ":features:favorites:favorites-api"
    const val featureFavoritesImpl = ":features:favorites:favorites-impl"
}

object GradleTemplates {
    const val kotlinLibrary = "template-kotlin-library.gradle"
    const val androidLibrary = "template-android-library.gradle"
    const val androidFeature = "template-android-feature.gradle"
}