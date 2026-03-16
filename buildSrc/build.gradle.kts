import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    `kotlin-dsl`
}

val toolchainVersion = libs.versions.java.toolchain.get().toInt()
val jvmBytecodeVersion = libs.versions.jvm.bytecode.get()
val appVersion = libs.versions.factory.data.flow.get()


repositories {
    mavenCentral()
    gradlePluginPortal()
}

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

    compilerOptions {
        
    }
}

dependencies {
    implementation(libs.spring.boot.gradle)
    implementation(libs.spring.dependency.management.gradle)
    implementation(libs.detekt.gradle)
    implementation(libs.kotlin.gradle)
    implementation(libs.kotlin.jpa.gradle)
    implementation(libs.kotlin.spring.gradle)
    implementation(libs.ktlint.gradle)
    implementation(files((libs as Any).javaClass.superclass.protectionDomain.codeSource.location))
}