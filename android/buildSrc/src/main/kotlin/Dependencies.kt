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
    const val kotlin = "1.4.10"
    const val androidX = "1.1.0"
    const val androidXKtxCore = "1.3.0"
    const val androidXKtxFragment = "1.2.5"
    const val googleServicesGradle = "4.3.4"
    const val crashlyticsGradle = "2.2.0"
    const val room = "2.2.0"
    const val lifecycle = "2.2.0"
    const val lifecycleKtx = "2.3.0-alpha07"
    const val dagger = "2.29.1"
    const val hilt = "2.29.1-alpha"
    const val coroutines = "1.3.9"
    const val retrofit = "2.9.0"
    const val okhttp = "4.9.0"
    const val flipper = "0.48.0"
}

object Libraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinXSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0"

    // AndroidX
    const val xAppCompat = "androidx.appcompat:appcompat:${Versions.androidX}"
    const val xCoreKtx = "androidx.core:core-ktx:${Versions.androidXKtxCore}"
    const val xFragmentKtx = "androidx.fragment:fragment-ktx:${Versions.androidXKtxFragment}"
    const val xLifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val xLifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtx}"
    const val xLifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleKtx}"
    const val xLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"
    const val xConstraintLayout = "androidx.constraintlayout:constraintlayout:2.0.2"
    const val xPreference = "androidx.preference:preference:1.1.0"
    const val xMaterial = "com.google.android.material:material:1.3.0-alpha03"
    const val xRecyclerView = "androidx.recyclerview:recyclerview:1.1.0"
    const val xPaging = "androidx.paging:paging-runtime:2.1.0"

    //Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    // Dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val hilt = "com.google.dagger:hilt-core:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    const val hiltAndroidX = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01"
    const val hiltAndroidXCompiler = "androidx.hilt:hilt-compiler:1.0.0-alpha01"

    // Coroutines
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitKotlinXSerialization = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"

    // Firebase, Play services
    const val firebaseAuth = "com.google.firebase:firebase-auth-ktx:19.4.0"
    const val firebaseAuthUI = "com.firebaseui:firebase-ui-auth:6.2.0"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx:17.5.0"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics:17.2.2"
    const val firebaseCloudMessaging = "com.google.firebase:firebase-messaging:20.0.0"

    // Flipper
    const val flipper = "com.facebook.flipper:flipper:${Versions.flipper}"
    const val flipperNoop = "com.facebook.flipper:flipper-noop:${Versions.flipper}"
    const val flipperSoloader = "com.facebook.soloader:soloader:0.9.0"
    const val flipperNetworkPlugin = "com.facebook.flipper:flipper-network-plugin:${Versions.flipper}"

    // Misc
    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.4"
    const val fragNav = "com.ncapdevi:frag-nav:3.2.0"

    // Tests
    const val testJunit = "junit:junit:4.13"
    const val testRunner = "androidx.test.ext:junit:1.1.1"
    const val testEspresso = "androidx.test.espresso:espresso-core:3.2.0"
    const val testRoom = "androidx.room:room-testing:${Versions.room}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"

    // Gradle
    const val gradleAndroid = "com.android.tools.build:gradle:4.1.0"
    const val gradleKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradleVersions = "com.github.ben-manes:gradle-versions-plugin:0.28.0"
}

object GradleTemplates {
    const val kotlinLibrary = "template-kotlin-library.gradle"
    const val androidLibrary = "template-android-library.gradle"
    const val androidFeature = "template-android-feature.gradle"
    const val androidWiring = "template-android-wiring.gradle"
    const val androidDevApp = "template-android-devapp.gradle"
}