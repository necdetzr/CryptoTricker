plugins {
    alias(libs.plugins.necdet.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.necdet.android.library.compose)
    alias(libs.plugins.necdet.android.hilt)

}

android {
    namespace = "com.necdetzr.analytics"
}

dependencies {
    api(platform(libs.firebase.bom))
    api(libs.firebase.analytics)
    implementation(libs.compose.runtime)
    implementation(libs.timber)

}