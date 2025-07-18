plugins {
    `kotlin-dsl` // This is essential for creating Gradle plugins
}

group = "com.necdetzr" // Your group ID (can be anything relevant)
version = "1.0.0"     // Your plugin version (can be anything, but useful for publishing)

// This block is crucial for defining and making your custom plugin available
gradlePlugin {
    plugins {
        create("androidApplicationConvention") {
            id = "com.necdetzr.android.application"
            implementationClass = "com.necdetzr.buildlogic.AndroidApplicationConventionPlugin"
        }
        create("androidLibraryConvention") {
            id = "com.necdetzr.android.library"
            implementationClass = "com.necdetzr.buildlogic.AndroidLibraryConventionPlugin"
        }
        create("hiltConvention") {
            id = "com.necdetzr.android.hilt"
            implementationClass = "com.necdetzr.buildlogic.HiltConventionPlugin"
        }
        create("composeConvention") {
            id = "com.necdetzr.android.compose"
            implementationClass = "com.necdetzr.buildlogic.ComposeConventionPlugin"
        }
        create("firebaseConvention") {
            id = "com.necdetzr.android.firebase"
            implementationClass = "com.necdetzr.buildlogic.FirebaseConventionPlugin"
        }
        create("commonDependencies") {
            id = "com.necdetzr.android.commondeps"
            implementationClass = "com.necdetzr.buildlogic.CommonDependenciesPlugin"
        }
        create("testingConvention") {
            id = "com.necdetzr.android.testing"
            implementationClass = "com.necdetzr.buildlogic.TestingConventionPlugin"
        }
    }
}


repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("com.android.tools.build:gradle:8.4.2")
    implementation(kotlin("gradle-plugin"))
}
