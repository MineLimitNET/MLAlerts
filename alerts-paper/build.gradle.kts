plugins {
    alias(libs.plugins.run.paper)
    alias(libs.plugins.blossom)
}

dependencies {
    compileOnly(libs.paper.api)
    compileOnly(project(":alerts-shared"))
}

sourceSets.main {
    blossom.javaSources {
        property("configurate", libs.versions.configurate)
    }
}

tasks {
    processResources {
        filesMatching("paper-plugin.yml") {
            expand(
                "version" to version,
                "apiVersion" to "1.21"
            )
        }
    }
    
    runServer {
        minecraftVersion("1.21.7")
        jvmArgs("-Xmx4G", "-Xms4G", "-Dcom.mojang.eula.agree=true")
        environment("PAPER_VELOCITY_SECRET", "MLALERTS_SECRET_987")
    }
    
    jar {
        from(project(":alerts-shared").sourceSets.main.get().output)
    }
}