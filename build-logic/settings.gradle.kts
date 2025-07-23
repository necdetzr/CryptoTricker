dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
        google()

    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"

