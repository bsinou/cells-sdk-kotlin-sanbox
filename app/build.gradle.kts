plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "org.sinou.kotlin.android.sampleapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.sinou.kotlin.android.sampleapp"
        minSdk = 24
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
        debug {
            version
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // Android and Jetpack Compose
    // Latest version can be found here: https://developer.android.com/jetpack/compose/bom
    implementation (platform(libs.compose.bom))
    implementation (libs.androidx.core.ktx)
    implementation (libs.material)
    implementation (libs.androidx.runtime)
    implementation (libs.androidx.ui)
    implementation (libs.androidx.ui.util)
    implementation (libs.androidx.ui.tooling)
    implementation (libs.androidx.foundation)
    implementation (libs.androidx.foundation.layout)
    implementation (libs.androidx.material3)
    implementation (libs.androidx.navigation.compose)
    implementation (libs.androidx.material.icons.core)
    implementation (libs.androidx.material.icons.extended)

    // Dependency injection with Koin https://insert-koin.io/
    implementation(platform(libs.koin.bom))
    implementation (libs.koin.core)
    implementation (libs.koin.android)
    implementation (libs.koin.androidx.workmanager)
    implementation (libs.koin.androidx.navigation)
    implementation (libs.koin.androidx.compose)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}