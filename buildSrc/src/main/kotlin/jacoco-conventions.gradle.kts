plugins {
    id("org.jetbrains.kotlin.jvm")
    jacoco
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.check)
    violationRules {
        rule {
            limit {
                minimum = 0.5.toBigDecimal()
            }
        }
    }
}