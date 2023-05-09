pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EVEnergyInfo"
include(":automotive")

include(":simple-gauge-android")
project(":simple-gauge-android").projectDir =  file("../snappos/simple-gauge-android/gaugelibrary")