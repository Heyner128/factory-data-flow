package me.heyner.manusim.test.annotation

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.context.ImportTestcontainers
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.postgresql.PostgreSQLContainer

interface ManusimTestContainers {
    companion object {
        @JvmStatic
        @Container
        @ServiceConnection
        val postgresql =
            PostgreSQLContainer("postgres:18-alpine")
    }
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ImportTestcontainers(ManusimTestContainers.Companion::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
annotation class ManusimIntegrationTest
