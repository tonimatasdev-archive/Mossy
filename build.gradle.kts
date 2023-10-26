@file:Suppress("VulnerableLibrariesLocal")

plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("dev.hollowcube:minestom-ce:5bcc72b911")

    implementation("org.jetbrains:annotations:24.0.1")
}

tasks.withType<Jar> {
    manifest {
        attributes(
            "Main-Class" to "net.tonimatasdev.mossy.Mossy",
            "Multi-Release" to true
        )
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
