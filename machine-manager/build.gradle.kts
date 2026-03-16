plugins {
    id("kotlin-conventions")
    id("spring-conventions")
    id("liquibase-conventions")
}

springBoot {
    mainClass = "me.heyner.factorydataflow.machinemanager.MachineManagerApplication"
}

dependencies {
    implementation(libs.spring.boot.starter.webmvc)
    implementation(libs.spring.boot.starter.data.jpa)
}