import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("jacoco-conventions")
    id("ktlint-conventions")
    id("detekt-conventions")
}

group = "me.heyner"
version = "0.0.1"

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }

    jvmToolchain(25)
}


repositories {
    mavenCentral()
}

