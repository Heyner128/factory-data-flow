plugins {
    id("org.jetbrains.kotlin.jvm")
    jacoco
}

val jacocoExclusions =
    listOf(
        "*.exception.*",
        "*.persistence.*",
        "*.stereotype.*",
        "*.*Id",
        "*.*Application*",
    )

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.check)
    violationRules {
        rule {
            includes = listOf("*.application.*")
            limit {
                minimum = 0.8.toBigDecimal()
            }
        }
        rule {
            excludes = listOf("*.application.*") + jacocoExclusions
            limit {
                minimum = 0.6.toBigDecimal()
            }
        }
    }
}
