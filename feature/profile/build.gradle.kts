plugins {
    alias(libs.plugins.necdet.android.feature)
    alias(libs.plugins.necdet.android.library)
    alias(libs.plugins.necdet.android.library.compose)
    alias(libs.plugins.necdet.android.hilt)
}

android {
    namespace = "com.necdetzr.profile"
}

dependencies {

    ksp(libs.hilt.compiler)

    implementation(project(":core:datastore"))
    implementation(project(":core:common"))
    implementation(libs.androidx.navigation.common.android)

}