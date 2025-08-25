
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class TestingConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        dependencies {
            add("testImplementation", "junit:junit:4.13.2")
            add("testImplementation","org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
            add("testImplementation","io.mockk:mockk:1.13.10")
            add("testImplementation","androidx.arch.core:core-testing:2.2.0")
            add("androidTestImplementation", "androidx.test.ext:junit:1.1.5")
            add("androidTestImplementation", "androidx.test.espresso:espresso-core:3.5.1")
        }
    }
}
