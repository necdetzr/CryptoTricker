plugins {
    alias(libs.plugins.necdet.android.application)
    alias(libs.plugins.necdet.android.hilt)
    alias(libs.plugins.necdet.android.firebase)
    alias(libs.plugins.necdet.android.common)
    alias(libs.plugins.necdet.android.test)
    alias(libs.plugins.compose.compiler)
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")

}

dependencies {
    implementation(project(":analytics"))
    implementation(project(":feature:settings"))
    implementation(project(":core:datastore"))
    implementation(project(":core:designsystem"))
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
}