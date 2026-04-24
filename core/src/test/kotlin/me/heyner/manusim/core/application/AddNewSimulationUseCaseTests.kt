package me.heyner.manusim.core.application

import me.heyner.manusim.core.domain.ManufacturingLineRepository
import me.heyner.manusim.core.domain.SimulationRepository
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddNewSimulationUseCaseTests(
    @Autowired val addNewSimulationUseCase: AddNewSimulationUseCase,
    @Autowired val manufacturingLineRepository: ManufacturingLineRepository,
    @Autowired val simulationRepository: SimulationRepository,
) {
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
