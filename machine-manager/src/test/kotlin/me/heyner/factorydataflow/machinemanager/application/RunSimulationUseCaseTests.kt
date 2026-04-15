package me.heyner.factorydataflow.machinemanager.application

import me.heyner.factorydataflow.machinemanager.domain.ManufacturingLineRepository
import me.heyner.factorydataflow.machinemanager.domain.Simulation
import me.heyner.factorydataflow.machinemanager.domain.SimulationExecutionLogRepository
import me.heyner.factorydataflow.machinemanager.domain.SimulationRepository
import me.heyner.factorydataflow.machinemanager.exception.SimulationExecutionException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RunSimulationUseCaseTests {
    @Autowired
    private lateinit var runSimulationUseCase: RunSimulationUseCase

    @Autowired
    private lateinit var manufacturingLineRepository: ManufacturingLineRepository

    @Autowired
    private lateinit var simulationRepository: SimulationRepository

    @Autowired
    private lateinit var simulationExecutionLogRepository: SimulationExecutionLogRepository

    private lateinit var testSimulation: Simulation

    @BeforeTest
    fun setup() {
        val manufacturingLine = manufacturingLineRepository.findAll().first()
        testSimulation = Simulation(manufacturingLine.id)
        simulationRepository.save(testSimulation)
    }

    @Test
    fun `given an already created simulation when simulation starts then event and start date are set`() {
        runSimulationUseCase.execute(
            testSimulation.id,
        )

        val createdEvent = simulationExecutionLogRepository.findFirstBySimulation(testSimulation.id)
        val simulation = simulationRepository.findById(testSimulation.id).get()

        assertThat(simulation.startDate).isNotNull()
        assertThat(createdEvent).isNotNull()
    }

    @Test
    fun `given an already started simulation when simulation starts then throws exception`() {
        runSimulationUseCase.execute(
            testSimulation.id,
        )
        assertThrows<SimulationExecutionException> { runSimulationUseCase.execute(testSimulation.id) }
    }
}
