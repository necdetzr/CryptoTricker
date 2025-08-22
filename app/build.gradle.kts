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
    implementation(project(":feature:settings:general"))
    implementation(project(":feature:settings:profile"))
    implementation(project(":feature:language"))
    implementation(project(":feature:profile"))
    implementation(project(":core:datastore"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(project(":feature:login"))
    implementation(project(":feature:register"))
    implementation(project(":feature:home:ui"))
    implementation(project(":feature:search"))
    implementation(project(":feature:market"))
    implementation(project(":feature:detail"))
    implementation(project(":core:testing"))
    implementation(project(":feature:favorite"))
    implementation(project(":feature:welcome"))
    implementation(project(":feature:splash"))

    ksp(libs.hilt.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
}