package me.heyner.factorydataflow.machinemanager.application

import me.heyner.factorydataflow.machinemanager.domain.ManufacturingLineRepository
import me.heyner.factorydataflow.machinemanager.domain.Simulation
import me.heyner.factorydataflow.machinemanager.domain.SimulationEventRepository
import me.heyner.factorydataflow.machinemanager.domain.SimulationRepository
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StartSimulationUseCaseTests {
    @Autowired
    private lateinit var startSimulationUseCase: StartSimulationUseCase

    @Autowired
    private lateinit var manufacturingLineRepository: ManufacturingLineRepository

    @Autowired
    private lateinit var simulationRepository: SimulationRepository

    @Autowired
    private lateinit var simulationEventRepository: SimulationEventRepository

    private lateinit var testSimulation: Simulation

    @BeforeTest
    fun setup() {
        val manufacturingLine = manufacturingLineRepository.findAll().first()
        testSimulation = Simulation(manufacturingLine.entityId)
        simulationRepository.save(testSimulation)
    }

    @Test
    fun `given an already created simulation when simulation starts then event and start date are set`() {
        startSimulationUseCase.execute(
            testSimulation.entityId,
        )

        val createdEvent = simulationEventRepository.findFirstBySimulation(testSimulation.entityId)
        val simulation = simulationRepository.findById(testSimulation.entityId).get()

        assertThat(simulation.startDate).isNotNull()
        assertThat(createdEvent).isNotNull()
    }
}
