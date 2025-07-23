import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl` // This is essential for creating Gradle plugins
}
kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}
group = "com.necdetzr" // Your group ID (can be anything relevant)
version = "1.0.0"     // Your plugin version (can be anything, but useful for publishing)

// This block is crucial for defining and making your custom plugin available
gradlePlugin {
    plugins {
        create("androidApplicationConvention") {
            id = "necdet.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        create("androidLibraryConvention") {
            id = "necdet.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        create("hiltConvention") {
            id = "necdet.android.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        create("composeConvention") {
            id = "necdet.android.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        create("firebaseConvention") {
            id = "necdet.android.firebase"
            implementationClass = "FirebaseConventionPlugin"
        }
        create("commonDependencies") {
            id = "necdet.android.commondeps"
            implementationClass = "CommonDependenciesPlugin"
        }
        create("testingConvention") {
            id = "necdet.android.testing"
            implementationClass = "TestingConventionPlugin"
        }
        create("libraryComposeConvention"){
            id = "necdet.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        create("libraryJacacoConvention"){
            id = "necdet.android.library.jacoco"
            implementationClass = "AndroidLibraryJacocoConventionPlugin"
        }
        create("futureConvention"){
            id = "necdet.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}


repositories {
    mavenCentral()
    google()
}

dependencies {
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}
