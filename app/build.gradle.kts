plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
    kotlin("plugin.serialization") version "1.9.0"
    id("kotlin-kapt")
}

android {
    namespace = "com.bold.bolttest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bold.bolttest"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11

    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        dataBinding = true
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.navigation.compose)
    implementation(libs.lottie.compose)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.coroutines.android)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(project(":main_weather"))
    implementation(project(":network"))

    //Koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}