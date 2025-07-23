plugins {
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.necdet.android.library)
    alias(libs.plugins.necdet.android.hilt)
    alias(libs.plugins.necdet.android.library.compose)
}

android {
    namespace = "com.necdetzr.datastore"
}

dependencies {
    api(libs.androidx.dataStore.preferences)
    implementation(project(":analytics"))
    implementation(project(":core:common"))
    api(libs.hilt.android.compiler)
    api(libs.hilt.android)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

}