@file:Suppress("VulnerableLibrariesLocal")

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar


plugins {
    id("java")
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
            "Launcher-Agent-Class" to "net.tonimatasdev.mossy.launcher.Agent",
            "Agent-Class" to "net.tonimatasdev.mossy.launcher.Agent",
            "Premain-Class" to "net.tonimatasdev.mossy.launcher.Agent",
            "Main-Class" to "net.tonimatasdev.mossy.launcher.Main",
            "Multi-Release" to true
        )
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
