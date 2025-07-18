package com.necdetzr.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FirebaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.google.gms.google-services")
        pluginManager.apply("com.google.firebase.crashlytics")
        dependencies {

            add("implementation", platform("com.google.firebase:firebase-bom:32.7.3"))

            add("implementation", "com.google.firebase:firebase-analytics")
            add("implementation", "com.google.firebase:firebase-crashlytics")
            add("implementation", "com.google.firebase:firebase-auth")
            add("implementation", "com.google.firebase:firebase-config")
            add("implementation", "com.google.firebase:firebase-firestore")
        }
    }
}
