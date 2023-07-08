import io.gitlab.arturbosch.detekt.Detekt

plugins {
    kotlin("multiplatform")
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    id("org.jlleitschuh.gradle.ktlint-idea") version "10.2.1"
    id("io.gitlab.arturbosch.detekt") version("1.23.0")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
}

ktlint {
    version.set("0.45.1")
}

detekt {
    toolVersion = "1.23.0"
    buildUponDefaultConfig = true
}

tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}

dependencies{
    add("implementation", "org.webjars.npm:mxgraph:4.2.2")
}
