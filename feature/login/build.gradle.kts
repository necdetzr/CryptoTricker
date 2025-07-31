plugins {
    alias(libs.plugins.necdet.android.feature)
    alias(libs.plugins.necdet.android.library)
    alias(libs.plugins.necdet.android.library.compose)
    alias(libs.plugins.necdet.android.hilt)
    alias(libs.plugins.necdet.android.firebase)
    alias(libs.plugins.necdet.android.test)

}

android {
    namespace = "com.necdetzr.login"
}

dependencies {

    ksp(libs.hilt.compiler)

    implementation(project(":core:datastore"))
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(libs.androidx.navigation.common.android)

}