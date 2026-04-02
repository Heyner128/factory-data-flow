package me.heyner.factorydataflow.machinemanager.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.jvm.optionals.getOrNull
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MachineRepositoryTests {
    @Autowired
    lateinit var machineRepository: MachineRepository

    lateinit var testMachine: Machine

    @BeforeEach
    fun setup() {
        testMachine = Machine("testMachine")
    }

    @Test
    fun `given a machine when saving then it should be retrievable by id`() {
        machineRepository.save(testMachine)
        val foundMachine = machineRepository.findById(testMachine.id).getOrNull()
        assertThat(foundMachine).isEqualTo(testMachine)
    }

    @Test
    fun `given a machine when updating then it should be retrievable by id`() {
        machineRepository.save(testMachine)
        testMachine.name = "updatedMachine"
        machineRepository.save(testMachine)
        val foundMachine = machineRepository.findById(testMachine.id).getOrNull()
        assertThat(foundMachine).isEqualTo(testMachine)
    }
}
