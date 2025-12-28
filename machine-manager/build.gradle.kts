plugins {
    id("java-conventions")
    id("spring-conventions")
}

springBoot {
    mainClass = "me.heyner.factorydataflow.machinemanager.MachineManagerApplication"
}

dependencies {
    implementation(libs.spring.boot.starter.web)
}