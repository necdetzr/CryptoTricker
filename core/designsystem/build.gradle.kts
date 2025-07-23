plugins {
    alias(libs.plugins.necdet.android.library)
    alias(libs.plugins.necdet.android.library.compose)
}

android {
    namespace = "com.necdetzr.designsystem"
}


dependencies {
    api(libs.compose.foundation.layout)
    api(libs.compose.material.icons.extended)
    api(libs.compose.material3)
    api(libs.compose.runtime)
    api(libs.compose.ui)
    api(libs.compose.ui.util)
    api(libs.compose.ui.graphics)
    implementation(project(":core:common"))
    api(libs.timber)
    api(libs.coil.kt.svg)
    api(libs.coil.kt)
    api(libs.coil.kt.compose)
    api(libs.coil.network.okhttp)

    implementation(libs.androidx.core.ktx)
}