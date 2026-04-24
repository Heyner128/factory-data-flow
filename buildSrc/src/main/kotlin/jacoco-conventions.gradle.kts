plugins {
    id("org.jetbrains.kotlin.jvm")
    jacoco
}

val domainFiles =
    listOf(
        "*.exception.*",
        "*.persistence.*",
        "*.stereotype.*",
        "*.*Id",
        "*.*Application*",
    )

val nonDomainFiles = listOf("*.application.*", "*.infrastructure.*")

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.check)
    violationRules {
        rule {
            includes = nonDomainFiles
            excludes = domainFiles
            limit {
                minimum = 0.8.toBigDecimal()
            }
        }
        rule {
            excludes = nonDomainFiles
            includes = domainFiles
            limit {
                minimum = 0.6.toBigDecimal()
            }
        }
    }
}
