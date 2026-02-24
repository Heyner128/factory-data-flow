

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

kotlin {
    jvmToolchain(25)
}

dependencies {
    implementation(libs.spring.boot.gradle)
    implementation(libs.spring.dependency.management.gradle)
    implementation(libs.detekt.gradle)
    implementation(libs.kotlin.gradle)
    implementation(libs.kotlin.spring.gradle)
    implementation(libs.ktlint.gradle)
    implementation(files((libs as Any).javaClass.superclass.protectionDomain.codeSource.location))
}