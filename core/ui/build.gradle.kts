plugins {
    alias(libs.plugins.necdet.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.necdet.android.library.compose)
    alias(libs.plugins.necdet.android.common)


}

android {
    namespace = "com.necdetzr.ui"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.36.0")

    implementation(project(":core:designsystem"))

}