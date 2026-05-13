plugins {
    id("kotlin-conventions")
    id("spring-conventions")
}

dependencies {
    implementation(libs.testcontainers.junit)
    implementation(libs.testcontainers.postgresql)
    implementation(libs.spring.boot.starter.test)
    implementation(libs.spring.boot.testcontainers)
}
