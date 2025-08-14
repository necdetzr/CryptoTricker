plugins {
    alias(libs.plugins.necdet.android.feature)
    alias(libs.plugins.necdet.android.library)
    alias(libs.plugins.necdet.android.library.compose)

}

android {
    namespace = "com.necdetzr.splash"
}

dependencies {
    implementation(project(":core:datastore"))
    implementation(libs.androidx.navigation.common.android)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

}