plugins {
    alias(libs.plugins.necdet.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.necdet.android.library.compose)

}
kotlin {
    jvmToolchain(17)
}

android {
    namespace = "com.necdetzr.common"
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime.compose)
    api(libs.androidx.lifecycle.runtime.compose)
    api(libs.kotlinx.serialization.json)
    implementation(libs.moshi.kotlin)
    implementation(libs.retrofit.converter.moshi)
    api(libs.compose.ui)
    implementation(libs.androidx.lifecycle.viewmodel.android)
}