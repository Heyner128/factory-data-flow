package me.heyner.factorydataflow.machinemanager.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SimulationExecutionLogRepositoryTests {
    @Autowired
    lateinit var manufacturingLineRepository: ManufacturingLineRepository

    @Autowired
    lateinit var simulationRepository: SimulationRepository

    @Autowired
    lateinit var simulationExecutionLogRepository: SimulationExecutionLogRepository

    lateinit var testSimulation: Simulation
    lateinit var testSimulationExecutionLog: SimulationExecutionLog

    @BeforeEach
    fun setup() {
        val testLine = manufacturingLineRepository.save(ManufacturingLine("testLine"))
        testSimulation = simulationRepository.save(Simulation(testLine.id))
        testSimulationExecutionLog = SimulationExecutionLog(testSimulation.id, SimulationStatus.STARTED)
    }

    @Test
    fun `given a simulation log when saving then it should be retrievable by id`() {
        simulationExecutionLogRepository.save(testSimulationExecutionLog)
        val foundEvent = simulationExecutionLogRepository.findById(testSimulationExecutionLog.id).getOrNull()
        assertThat(foundEvent).isEqualTo(testSimulationExecutionLog)
    }

    @Test
    fun `given a simulation log when updating end date then it should be retrievable by id`() {
        simulationExecutionLogRepository.save(testSimulationExecutionLog)
        testSimulationExecutionLog.endDate = OffsetDateTime.now()
        simulationExecutionLogRepository.save(testSimulationExecutionLog)
        val foundEvent = simulationExecutionLogRepository.findById(testSimulationExecutionLog.id).getOrNull()
        assertThat(foundEvent).isEqualTo(testSimulationExecutionLog)
    }
}
