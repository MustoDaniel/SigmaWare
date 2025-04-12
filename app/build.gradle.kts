plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.sigmaware"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sigmaware"
        minSdk = 26
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
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.android)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.play.services.maps)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.security.crypto.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.androidx.biometric.ktx)
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.foundation.layout.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Mappe Compose
    implementation(libs.maps.compose)

    // Play Services Maps
    implementation(libs.play.services.maps.v1820)

    // Heatmap utility
    implementation(libs.android.maps.utils)

    // KTX per le mappe
    implementation(libs.maps.ktx)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)

    // Biometric
    implementation(libs.androidx.biometric)

    // Stripe
    implementation(libs.stripe.android)

    // Compose
    implementation(libs.androidx.navigation.compose.v275)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Firestore
    implementation(libs.firebase.firestore.ktx.v2491)

    // Maps
    implementation(libs.maps.compose.v2114)
    implementation(libs.play.services.maps)

    // Date picker
    implementation(libs.datetime)

}