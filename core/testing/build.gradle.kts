plugins {
    alias(libs.plugins.necdet.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.necdet.android.hilt)
    alias(libs.plugins.necdet.android.test)
}

android {
    namespace = "com.necdetzr.testing"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    api(libs.kotlinx.coroutines.test)
    implementation(project(":core:common"))

    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android.testing)
    implementation(libs.kotlinx.datetime)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
    implementation(libs.androidx.test.rules)



}