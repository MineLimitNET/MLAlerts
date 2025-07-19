import com.diffplug.gradle.spotless.SpotlessPlugin

plugins {
    id("java-library")
    alias(libs.plugins.spotless)
}

allprojects {
    apply {
        plugin<SpotlessPlugin>()
    }

    spotless {
        java {
            licenseHeaderFile(rootProject.file("HEADER"))
            target("**/*.java")
        }
    }
}

subprojects {
    apply {
        plugin<JavaLibraryPlugin>()
    }

    group = "net.strokkur"
    version = "1.0.0-DEV"

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
        toolchain.languageVersion = JavaLanguageVersion.of(21)
    }
}