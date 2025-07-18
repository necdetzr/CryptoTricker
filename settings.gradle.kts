pluginManagement {
    includeBuild("build-logic")


    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

rootProject.name = "LoodosCrypto"
include(":app")
include(":analytics")
include(":core:common")
include(":feature:settings")
include(":core:designsystem")
include(":core:datastore")

include(":feature:profile")
include(":feature:home")
include(":feature:search")
include(":feature:market")
include(":feature:login")
include(":feature:register")
include(":feature:splash")
include(":feature:favorite")
include(":feature:detail")
include(":core:network")
include(":core:monitor")
include(":core:resources")
include(":core:ui")