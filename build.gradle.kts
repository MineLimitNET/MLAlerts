plugins {
    id("java")
}

allprojects {
    apply {
        plugin<JavaPlugin>()
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