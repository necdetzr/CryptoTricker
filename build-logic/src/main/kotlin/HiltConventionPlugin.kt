package com.necdetzr.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.google.dagger.hilt.android")
        pluginManager.apply("com.google.devtools.ksp")

        dependencies {
            add("implementation", "com.google.dagger:hilt-android:2.51.1")
            add("ksp", "com.google.dagger:hilt-compiler:2.51.1")
            add("implementation","androidx.hilt:hilt-navigation-compose:1.1.0")

        }
    }
}
