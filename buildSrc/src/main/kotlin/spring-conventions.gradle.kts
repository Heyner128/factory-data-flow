import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.withType

val libs = the<LibrariesForLibs>()

plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

tasks.withType<Test> {
    doFirst {
        environment("SPRING_PROFILES_ACTIVE", "test,default")
    }
    useJUnitPlatform()
}

tasks.register("run") {
    finalizedBy(tasks.bootRun)
}

tasks.register("devRun") {
    doFirst {
        tasks.bootRun.get().environment("SPRING_PROFILES_ACTIVE", "dev,default")
    }
    finalizedBy(tasks.bootRun)
}

dependencies {
    developmentOnly(libs.spring.boot.devtools)
    runtimeOnly(libs.driver.postgresql)
}