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
    implementation("dev.hollowcube:minestom-ce:34558e75ee")
    implementation("de.articdive:jnoise:3.0.2")

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
