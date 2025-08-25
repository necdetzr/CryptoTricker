plugins {
    alias(libs.plugins.necdet.android.feature)
    alias(libs.plugins.necdet.android.library)
    alias(libs.plugins.necdet.android.library.compose)
    alias(libs.plugins.necdet.android.hilt)
    alias(libs.plugins.necdet.android.firebase)
    alias(libs.plugins.necdet.android.test)

}

android {
    namespace = "com.necdetzr.market"
}

dependencies {
    ksp(libs.hilt.compiler)

    implementation(project(":core:datastore"))
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:testing"))
    implementation(project(":feature:home:component"))
    implementation(libs.androidx.navigation.common.android)
}