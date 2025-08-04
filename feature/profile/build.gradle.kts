plugins {
    alias(libs.plugins.necdet.android.feature)
    alias(libs.plugins.necdet.android.library)
    alias(libs.plugins.necdet.android.library.compose)
    alias(libs.plugins.necdet.android.hilt)
    alias(libs.plugins.necdet.android.firebase)
}

android {
    namespace = "com.necdetzr.profile"
}

dependencies {

    ksp(libs.hilt.compiler)

    implementation(project(":core:datastore"))
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(libs.androidx.navigation.common.android)

}