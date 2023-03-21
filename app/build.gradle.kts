plugins {
    id("com.android.application")
    kotlin("android")
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
        }
    }
}

dependencies {

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.androidx.viewmodel.compose)

    implementation(libs.compose.foundation)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material.iconscore)
    implementation(libs.compose.material.iconsext)

    implementation(libs.wear.compose.material)
    implementation(libs.wear.compose.foundation)
    implementation(libs.wear.compose.navigation)

    implementation(libs.horologist.composables)
    implementation(libs.horologist.compose.layout)
    implementation(libs.wear.input)

    implementation(libs.androidx.splashscreen)

    debugImplementation(libs.compose.ui.tooling)
}
