plugins {
    id("org.jetbrains.kotlin.jvm")
    jacoco
}

val jacocoExclusions =
    listOf(
        "**/domain/**",
        "**/exception/**",
        "**/persistence/**",
    )

tasks.jacocoTestReport {
    classDirectories.setFrom(
        classDirectories.files.map {
            fileTree(it) { exclude(jacocoExclusions) }
        },
    )
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.check)
    classDirectories.setFrom(
        classDirectories.files.map {
            fileTree(it) { exclude(jacocoExclusions) }
        },
    )
    violationRules {
        rule {
            limit {
                minimum = 0.7.toBigDecimal()
            }
        }
    }
}
