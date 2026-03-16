import org.gradle.accessors.dm.LibrariesForLibs
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val libs = the<LibrariesForLibs>()

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("jacoco-conventions")
    id("ktlint-conventions")
    id("detekt-conventions")
}

val toolchainVersion = libs.versions.java.toolchain.get().toInt()
val jvmBytecodeVersion = libs.versions.jvm.bytecode.get()
val appVersion = libs.versions.factory.data.flow.get()

group = "me.heyner"
version = appVersion


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(toolchainVersion))
    }
    sourceCompatibility = JavaVersion.toVersion(jvmBytecodeVersion)
    targetCompatibility = JavaVersion.toVersion(jvmBytecodeVersion)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
        jvmTarget.set(JvmTarget.fromTarget(jvmBytecodeVersion))
    }

    jvmToolchain(toolchainVersion)
}


repositories {
    mavenCentral()
}


