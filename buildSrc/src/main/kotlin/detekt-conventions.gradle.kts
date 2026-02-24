import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.gradle.accessors.dm.LibrariesForLibs
plugins {
    id("org.jetbrains.kotlin.jvm")
    id("io.gitlab.arturbosch.detekt")
}

val libs = the<LibrariesForLibs>()

val targetJvm = java.toolchain.languageVersion.map { it.asInt().toString() }

detekt {
    buildUponDefaultConfig = true
    allRules = false
}


tasks.withType<Detekt>().configureEach {
    jvmTarget = targetJvm.get()
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = targetJvm.get()
}

dependencies {
    detektPlugins(libs.detekt.ktlint)
}