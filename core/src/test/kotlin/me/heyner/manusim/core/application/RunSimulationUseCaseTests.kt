package me.heyner.manusim.core.application

import me.heyner.manusim.core.ManusimTest
import me.heyner.manusim.core.domain.Simulation
import me.heyner.manusim.core.domain.SimulationExecutionLogRepository
import me.heyner.manusim.core.domain.SimulationId
import me.heyner.manusim.core.domain.SimulationRepository
import me.heyner.manusim.core.domain.SimulationStatus
import me.heyner.manusim.core.exception.SimulationExecutionException
import me.heyner.manusim.core.exception.SimulationNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.BeforeTest
import kotlin.test.Test

@ManusimTest
class RunSimulationUseCaseTests(
    @Autowired private val runSimulationUseCase: RunSimulationUseCase,
    @Autowired private val simulationRepository: SimulationRepository,
    @Autowired private val simulationExecutionLogRepository: SimulationExecutionLogRepository,
) {
    private lateinit var testSimulation: Simulation

    @BeforeTest
    fun setup() {
        simulationExecutionLogRepository.deleteAll()
        simulationRepository.deleteAll()
        testSimulation = Simulation()
        simulationRepository.save(testSimulation)
    }

    @Test
    fun `when simulation starts then event and start date are set`() {
        runSimulationUseCase.execute(
            testSimulation.id,
        )

        val createdEvent = simulationExecutionLogRepository.findFirstBySimulation(testSimulation.id)
        val simulation = simulationRepository.findById(testSimulation.id).get()

        assertThat(simulation.startDate).isNotNull()
        assertThat(simulation.status).isEqualTo(SimulationStatus.RUNNING)
        assertThat(createdEvent).isNotNull()
    }

    @Test
    fun `when simulation starts then the initial number of pieces is set`() {
        runSimulationUseCase.execute(
            testSimulation.id,
        )
        val simulation = simulationRepository.findById(testSimulation.id).get()
        assertThat(simulation.state).isNotNull()
        assertThat(simulation.state?.piecesPending).isEqualTo(0)
        assertThat(simulation.state?.piecesFinished).isEqualTo(0)
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
