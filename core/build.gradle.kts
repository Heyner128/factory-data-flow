plugins {
    id("kotlin-conventions")
    id("spring-conventions")
    id("liquibase-conventions")
}

dependencies {
    implementation(libs.spring.boot.starter.webmvc)
    implementation(libs.spring.boot.starter.data.jpa)
    testImplementation(libs.spring.boot.starter.test)
}
