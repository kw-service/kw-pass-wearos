plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.application")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "dev.yjyoon.kwlibrarywearos"
    compileSdk = 33

    defaultConfig {
        applicationId = "dev.yjyoon.kwlibrarywearos"
        minSdk = 30
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
        vectorDrawables {
            useSupportLibrary = true
        }

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
        freeCompilerArgs += "-opt-in=androidx.wear.compose.material.ExperimentalWearMaterialApi"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/INDEX.LIST"
        }
    }
}

dependencies {
    implementation(libs.bundles.androidx)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.wear)
    implementation(libs.bundles.horologist)
    implementation(libs.bundles.retrofit)

    implementation(libs.bundles.tikxml)
    kapt(libs.tickaroo.tikxml.processor)

    implementation(libs.coil.compose)
    implementation(libs.zxing.android.embedded)

    implementation(libs.google.hilt.android)
    kapt(libs.google.hilt.compiler)

    debugImplementation(libs.compose.ui.tooling)
}

kapt {
    correctErrorTypes = true
}
