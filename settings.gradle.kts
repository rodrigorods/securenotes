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

rootProject.name = "SecureNotes"
include(":app")

include(":notes-domain")
project(":notes-domain").projectDir = File(rootDir, "domain/notes")

include(":notes-data")
project(":notes-data").projectDir = File(rootDir, "data/notes")

include(":notes-ui")
project(":notes-ui").projectDir = File(rootDir, "presentation/notes")

include(":notes-feature-injector")
project(":notes-feature-injector").projectDir = File(rootDir, "injector/notes")
