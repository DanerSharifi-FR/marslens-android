plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    // Serializers used to read API resps
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "fr.danersharifi.marslens"
    compileSdk = 37

    defaultConfig {
        applicationId = "fr.danersharifi.marslens"
        minSdk = 29
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    // Preview annotations for my composable files
    implementation(libs.androidx.ui.tooling.preview)
    // Rendering tools needed in debug builds
    debugImplementation(libs.androidx.ui.tooling)
    // HTTP reqs & conversion of JSON resps into Kotlin obj
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization.converter)
    implementation(libs.kotlinx.serialization.json)
}
