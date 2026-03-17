import dev.detekt.gradle.Detekt
import dev.detekt.gradle.DetektCreateBaselineTask
import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("dev.detekt")
}

val libs = the<LibrariesForLibs>()

val targetJvm = java.toolchain.languageVersion.map { it.asInt().toString() }

detekt {
    buildUponDefaultConfig = true
    allRules = false
}


tasks.withType<Detekt>().configureEach {
    jvmTarget = targetJvm.get()
    config.setFrom(files("${rootProject.projectDir}/detekt.yaml"))
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = targetJvm.get()
}

dependencies {
    detektPlugins(libs.detekt.ktlint)
}