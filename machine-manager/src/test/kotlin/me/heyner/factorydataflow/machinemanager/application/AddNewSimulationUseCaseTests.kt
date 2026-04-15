package me.heyner.factorydataflow.machinemanager.application

import me.heyner.factorydataflow.machinemanager.domain.ManufacturingLineRepository
import me.heyner.factorydataflow.machinemanager.domain.SimulationRepository
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddNewSimulationUseCaseTests {
    @Autowired
    private lateinit var addNewSimulationUseCase: AddNewSimulationUseCase

    @Autowired
    private lateinit var manufacturingLineRepository: ManufacturingLineRepository

    @Autowired
    private lateinit var simulationRepository: SimulationRepository

    @Test
    fun `given an already created manufacturing line when simulation is added then simulation is created`() {
        val manufacturingLine = manufacturingLineRepository.findAll().first()
        addNewSimulationUseCase.execute(manufacturingLine.id)
        val simulations = simulationRepository.findAll()

        assertThat(simulations).isNotEmpty()
        assertThat(simulations.first().manufacturingLine).isEqualTo(
            manufacturingLine.id,
        )
    }
}
