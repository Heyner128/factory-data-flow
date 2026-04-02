package me.heyner.factorydataflow.machinemanager.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.jvm.optionals.getOrNull
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ManufacturingLineRepositoryTests {
    @Autowired
    lateinit var manufacturingLineRepository: ManufacturingLineRepository

    lateinit var testManufacturingLine: ManufacturingLine

    @BeforeEach
    fun setup() {
        testManufacturingLine = ManufacturingLine("testLine")
    }

    @Test
    fun `given a manufacturing line when saving then it should be retrievable by id`() {
        manufacturingLineRepository.save(testManufacturingLine)
        val foundLine = manufacturingLineRepository.findById(testManufacturingLine.id).getOrNull()
        assertThat(foundLine).isEqualTo(testManufacturingLine)
    }

    @Test
    fun `given a manufacturing line when updating then it should be retrievable by id`() {
        manufacturingLineRepository.save(testManufacturingLine)
        testManufacturingLine.name = "updatedLine"
        manufacturingLineRepository.save(testManufacturingLine)
        val foundLine = manufacturingLineRepository.findById(testManufacturingLine.id).getOrNull()
        assertThat(foundLine).isEqualTo(testManufacturingLine)
    }
}
