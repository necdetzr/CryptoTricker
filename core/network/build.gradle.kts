plugins {
    alias(libs.plugins.necdet.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.necdet.android.hilt)
    alias(libs.plugins.necdet.android.common)
    alias(libs.plugins.necdet.android.library.compose)
    alias(libs.plugins.necdet.android.firebase)

}

android {
    namespace = "com.necdetzr.network"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

}