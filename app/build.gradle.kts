@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    kotlin("kapt")
}

android {
    namespace = "work.curioustools.composerecipes"
    compileSdk = 34
    buildToolsVersion = "34.0.0"

    defaultConfig {
        applicationId = "work.curioustools.composerecipes"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    dependenciesInfo {
        includeInApk = true
        includeInBundle = true
    }

}
kapt {
    correctErrorTypes = true
}

dependencies {
    //arch: lifecycles
    implementation(libs.lifecycle.runtime.ktx)

    //arch: dagger, hilt
    implementation(libs.hilt.android)
    //implementation(libs.androidx.hilt.work) //hilt for work
    //implementation(libs.androidx.hilt.navigation.fragment) //hilt for fragments
    implementation(libs.androidx.hilt.navigation.compose) //hilt for compose
    kapt(libs.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)

    //arch: coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // arch: logging
    implementation(libs.timber)

    //arch: work managers

    //arch:retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.logginginterceptor)
    implementation(libs.okhttp.urlconnection)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    //cloud:firebase
    //cloud:analytics

    //ui: glide and coil
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation (libs.glide)

    //ui: material and androidx.core
    implementation(libs.core.ktx)
    implementation(libs.material3)

    //ui: compose
    implementation(platform(libs.compose.bom))
    implementation(libs.activity.compose)
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)

    //ui: browser,camera,exoplayer(video),recyclerview,constraint layout


    //debug ui: compose
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    //testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)

}
