rootProject.name = "MLAlerts"

sequenceOf("paper", "shared", "velocity").forEach { 
    include("alerts-$it")
}