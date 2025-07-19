plugins {
    alias(libs.plugins.run.velocity)
    alias(libs.plugins.shadow)
    alias(libs.plugins.blossom)
}

dependencies {
    compileOnly(libs.velocity.api)
    annotationProcessor(libs.velocity.api)
    compileOnly(project(":alerts-shared"))
}

sourceSets.main {
    blossom.javaSources {
        property("version", project.version.toString())
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
    
    runVelocity {
        velocityVersion(libs.versions.velocity.get())
        environment("VELOCITY_FORWARDING_SECRET", "MLALERTS_SECRET_987")
        
        downloadPlugins.hangar("ViaVersion", "5.4.2-SNAPSHOT+784")
        downloadPlugins.hangar("ViaBackwards", "5.4.2-SNAPSHOT+461")
        downloadPlugins.url("https://download.luckperms.net/1594/velocity/LuckPerms-Velocity-5.5.9.jar")
    }

    jar {
        archiveBaseName = "MLAlerts-Velocity"
        archiveClassifier = "noshade"
        from(project(":alerts-shared").sourceSets.main.get().output)
    }
    
    shadowJar {
        archiveBaseName = "MLAlerts-Velocity"
        archiveClassifier = null
        include("org.spongepowered")
        relocate("org.spongepowered", "net.strokkur.alerts.velocity.libs")
    }
}