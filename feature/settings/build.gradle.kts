
plugins {
        alias(libs.plugins.android.library)
        alias(libs.plugins.jetbrains.kotlin.android)
        id("org.jetbrains.kotlin.plugin.compose")

        id("dagger.hilt.android.plugin")
        id("kotlin-kapt")
}




android {
    namespace = "com.example.settings"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    //Coil
    implementation("io.coil-kt:coil-compose:2.4.0")
    //Data Store
    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    kapt("com.google.dagger:hilt-compiler:2.51.1") // Hilt compiler sürümünü plugin sürümüne göre uyumlu seç

    //Dependencies
    implementation("androidx.navigation:navigation-compose:2.9.0")
    //Firebase BOM
    implementation(platform("com.google.firebase:firebase-bom:33.14.0"))
    //Firebase Analytics
    implementation("com.google.firebase:firebase-analytics")
    //Firebase Auth
    implementation("com.google.firebase:firebase-auth:22.3.0")
    // Firebase Crashlytics and Analytics
    implementation("com.google.firebase:firebase-crashlytics-ndk")
    implementation("com.google.firebase:firebase-analytics-ktx:22.5.0")
    //Firebase Remote Config
    implementation("com.google.firebase:firebase-config")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    implementation(project(":analytics"))
    //Timber
    implementation ("com.jakewharton.timber:timber:5.0.1")

    // Hilt Navigation Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //Gson
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //Material Icons
    implementation("androidx.compose.material:material-icons-extended:1.7.8")
    //Firestore
    implementation ("com.google.firebase:firebase-firestore-ktx:24.10.3")
    implementation(project(":core:designsystem"))
    implementation(project(":core:datastore"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}