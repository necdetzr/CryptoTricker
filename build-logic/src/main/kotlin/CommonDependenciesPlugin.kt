    package com.necdetzr.buildlogic

    import org.gradle.api.Plugin
    import org.gradle.api.Project
    import org.gradle.kotlin.dsl.dependencies

    class CommonDependenciesPlugin : Plugin<Project> {
        override fun apply(target: Project) = with(target) {
            dependencies {
                add("implementation", "io.coil-kt:coil-compose:2.4.0")

                add("implementation", "com.squareup.retrofit2:retrofit:2.9.0")
                add("implementation", "com.squareup.retrofit2:converter-gson:2.9.0")

                add("implementation", "com.jakewharton.timber:timber:5.0.1")

                add("implementation", "androidx.navigation:navigation-compose:2.9.0")
                add("implementation", "androidx.core:core-ktx:1.13.1")

                add("implementation", "com.google.android.material:material:1.12.0")

                add("implementation", "androidx.compose.material:material-icons-extended")


            }
        }
    }
