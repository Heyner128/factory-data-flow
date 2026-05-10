package me.heyner.manusim.core.application

import me.heyner.manusim.core.ManusimTest
import me.heyner.manusim.core.domain.Machine
import me.heyner.manusim.core.domain.MachineRepository
import me.heyner.manusim.core.domain.Simulation
import me.heyner.manusim.core.domain.SimulationId
import me.heyner.manusim.core.domain.SimulationRepository
import me.heyner.manusim.core.domain.Status
import me.heyner.manusim.core.domain.TimeGenerator
import me.heyner.manusim.core.exception.SimulationExecutionException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.testcontainers.shaded.org.awaitility.Awaitility
import java.time.Duration
import kotlin.test.Test
import kotlin.test.fail

@ManusimTest
class RunSimulationUseCaseTests(
    @Autowired private val runSimulationUseCase: RunSimulationUseCase,
    @Autowired private val simulationRepository: SimulationRepository,
    @Autowired private val machineRepository: MachineRepository,
) {
    private var duration: Duration = Duration.ofMillis(1500)

    @AfterEach
    fun tearDown() {
        simulationRepository
            .findAll()
            .filter { it.status == Status.RUNNING }
            .forEach { waitUnitFinished(it.id) }
    }

    private fun createSimulation(): Simulation {
        val testSimulation = Simulation()
        testSimulation.machine = createMachine().id
        simulationRepository.save(testSimulation)
        return testSimulation
    }

    private fun createMachine(): Machine {
        val testMachine =
            Machine(
                TimeGenerator(
                    Duration.ofMillis(50),
                    Duration.ofMillis(100),
                ),
            )
        machineRepository.save(testMachine)
        return testMachine
    }

    private fun waitUnitFinished(simulationId: SimulationId) {
        Awaitility
            .await()
            .atMost(duration.plusSeconds(1))
            .pollInterval(Duration.ofMillis(100))
            .until {
                val simulation = simulationRepository.findById(simulationId).get()
                simulation.status == Status.NOT_RUNNING
            }
    }

    @Test
    fun `when simulation starts then the execution state is created`() {
        val testSimulation = createSimulation()
        val execution =
            runSimulationUseCase.execute(
                testSimulation.id,
                duration,
            )
        val simulation = simulationRepository.findById(testSimulation.id).get()
        assertThat(simulation.status).isEqualTo(Status.RUNNING)
        assertThat(execution).isNotNull()
        assertThat(execution.duration).isGreaterThan(Duration.ZERO)
        assertThat(execution.durationElapsed).isEqualTo(Duration.ZERO)
    }

    @Test
    fun `when simulation starts then the initial execution state is created`() {
        val testSimulation = createSimulation()
        val execution =
            runSimulationUseCase.execute(
                testSimulation.id,
                duration,
            )
        assertThat(execution.state).isNotNull()
        assertThat(execution.state?.piecesPending).isEqualTo(0)
        assertThat(execution.state?.piecesFinished).isEqualTo(0)
        assertThat(execution.events.size).isNotZero()
    }

    @Test
    fun `given a simulation without machine when simulation starts then it throws exception`() {
        val testSimulation = createSimulation()
        testSimulation.machine = null
        simulationRepository.save(testSimulation)
        assertThrows<SimulationExecutionException> { runSimulationUseCase.execute(testSimulation.id, duration) }
    }

    @Test
    fun `given an already started simulation when simulation starts then throws exception`() {
        val testSimulation = createSimulation()
        runSimulationUseCase.execute(
            testSimulation.id,
            duration,
        )
        assertThrows<SimulationExecutionException> { runSimulationUseCase.execute(testSimulation.id, duration) }
    }

    @Test
    fun `given a non existent simulation when simulation starts then throws exception`() {
        assertThrows<NoSuchElementException> { runSimulationUseCase.execute(SimulationId(), duration) }
    }

    @Test
    fun `when simulation duration is elapsed then status is not running`() {
        val testSimulation = createSimulation()
        val execution =
            runSimulationUseCase.execute(
                testSimulation.id,
                duration,
            )
        Thread.sleep(duration.plusSeconds(1).toMillis())
        val simulation = simulationRepository.findById(testSimulation.id).get()
        assertThat(simulation.status).isEqualTo(Status.NOT_RUNNING)
        assertThat(execution.events.size).isZero()
    }

    @Test
    fun `when simulation starts then should be at least one event for the whole duration`() {
        val testSimulation = createSimulation()
        val testMachine = machineRepository.findById(testSimulation.machine!!).get()
        val execution =
            runSimulationUseCase.execute(
                testSimulation.id,
                duration,
            )
        while (execution.durationElapsed < execution.duration) {
            if (execution.events.isEmpty()) {
                fail("Event queue is empty")
            }
            Thread.sleep(testMachine.timeGenerator.to.toMillis())
        }
    }

    @Test
    fun `when simulation starts then the pieces produced should grow over time`() {
        val testSimulation = createSimulation()
        val testMachine = machineRepository.findById(testSimulation.machine!!).get()
        val execution =
            runSimulationUseCase.execute(
                testSimulation.id,
                duration,
            )
        while (execution.durationElapsed < execution.duration) {
            val piecesBefore = execution.state!!.piecesFinished
            Thread.sleep(testMachine.timeGenerator.to.toMillis())
            val piecesAfter = execution.state!!.piecesFinished
            assertThat(piecesAfter).isGreaterThan(piecesBefore)
        }
    }
}
