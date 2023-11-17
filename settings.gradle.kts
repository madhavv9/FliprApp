pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        jcenter()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        jcenter()

    }
}

rootProject.name = "FliprApp"
include(":app")
