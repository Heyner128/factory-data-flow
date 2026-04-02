package me.heyner.factorydataflow.machinemanager.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SimulationEventRepositoryTests {
    @Autowired
    lateinit var manufacturingLineRepository: ManufacturingLineRepository

    @Autowired
    lateinit var simulationRepository: SimulationRepository

    @Autowired
    lateinit var simulationEventRepository: SimulationEventRepository

    lateinit var testSimulation: Simulation
    lateinit var testSimulationEvent: SimulationEvent

    @BeforeEach
    fun setup() {
        val testLine = manufacturingLineRepository.save(ManufacturingLine("testLine"))
        testSimulation = simulationRepository.save(Simulation(testLine.entityId))
        testSimulationEvent = SimulationEvent(testSimulation.entityId, SimulationEventType.START)
    }

    @Test
    fun `given a simulation event when saving then it should be retrievable by id`() {
        simulationEventRepository.save(testSimulationEvent)
        val foundEvent = simulationEventRepository.findById(testSimulationEvent.id).getOrNull()
        assertThat(foundEvent).isEqualTo(testSimulationEvent)
    }

    @Test
    fun `given a simulation event when updating end date then it should be retrievable by id`() {
        simulationEventRepository.save(testSimulationEvent)
        testSimulationEvent.endDate = OffsetDateTime.now()
        simulationEventRepository.save(testSimulationEvent)
        val foundEvent = simulationEventRepository.findById(testSimulationEvent.id).getOrNull()
        assertThat(foundEvent).isEqualTo(testSimulationEvent)
    }
}
