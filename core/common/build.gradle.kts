plugins {
    alias(libs.plugins.necdet.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.necdet.android.library.compose)
    alias(libs.plugins.necdet.android.hilt)
    alias(libs.plugins.necdet.android.test)


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
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android.testing)
    implementation(libs.kotlinx.datetime)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
    implementation(libs.androidx.test.rules)
    api(libs.kotlinx.coroutines.test)

    implementation(libs.androidx.lifecycle.viewmodel.android)
}