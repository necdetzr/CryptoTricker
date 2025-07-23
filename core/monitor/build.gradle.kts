plugins {
    alias(libs.plugins.necdet.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.necdet.android.hilt)
    alias(libs.plugins.necdet.android.library.compose)

}

android {
    namespace = "com.necdetzr.monitor"
}

dependencies {
    implementation(project( ":core:common"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

}