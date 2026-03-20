package me.heyner.factorydataflow.machinemanager

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import kotlin.test.Test

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class MachineManagerApplicationTests {
    @Test
    fun contextLoads() {
        // intentionally left blank, tests if the spring context loads
    }
}
