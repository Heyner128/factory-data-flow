package me.heyner.factorydataflow.machinemanager.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SimulationRepositoryTests {
    @Autowired
    lateinit var manufacturingLineRepository: ManufacturingLineRepository

    @Autowired
    lateinit var simulationRepository: SimulationRepository

    lateinit var testLine: ManufacturingLine
    lateinit var testSimulation: Simulation

    @BeforeEach
    fun setup() {
        testLine = manufacturingLineRepository.save(ManufacturingLine("testLine"))
        testSimulation = Simulation(testLine.id)
    }

    @Test
    fun `given a simulation when saving then it should be retrievable by id`() {
        simulationRepository.save(testSimulation)
        val foundSimulation = simulationRepository.findById(testSimulation.id).getOrNull()
        assertThat(foundSimulation).isEqualTo(testSimulation)
    }

    @Test
    fun `given a simulation when updating end date then it should be retrievable by id`() {
        simulationRepository.save(testSimulation)
        testSimulation.endDate = OffsetDateTime.now()
        simulationRepository.save(testSimulation)
        val foundSimulation = simulationRepository.findById(testSimulation.id).getOrNull()
        assertThat(foundSimulation).isEqualTo(testSimulation)
    }
}
