plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ktlint)
    // Serializers used to read API resps
    alias(libs.plugins.kotlin.serialization)
}
