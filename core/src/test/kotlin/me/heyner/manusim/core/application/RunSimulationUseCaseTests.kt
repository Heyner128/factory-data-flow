package me.heyner.manusim.core.application

import me.heyner.manusim.core.domain.ManufacturingLineRepository
import me.heyner.manusim.core.domain.Simulation
import me.heyner.manusim.core.domain.SimulationExecutionLogRepository
import me.heyner.manusim.core.domain.SimulationId
import me.heyner.manusim.core.domain.SimulationRepository
import me.heyner.manusim.core.exception.SimulationExecutionException
import me.heyner.manusim.core.exception.SimulationNotFoundException
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

    @Test
    fun `given a non existent simulation when simulation starts then throws exception`() {
        assertThrows<SimulationNotFoundException> { runSimulationUseCase.execute(SimulationId()) }
    }
}
