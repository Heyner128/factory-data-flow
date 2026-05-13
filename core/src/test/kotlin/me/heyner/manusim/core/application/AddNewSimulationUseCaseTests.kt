package me.heyner.manusim.core.application

import me.heyner.manusim.core.domain.SimulationRepository
import me.heyner.manusim.core.domain.Status
import me.heyner.manusim.test.annotation.ManusimIntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.Test

@ManusimIntegrationTest
class AddNewSimulationUseCaseTests(
    @Autowired private val addNewSimulationUseCase: AddNewSimulationUseCase,
    @Autowired private val simulationRepository: SimulationRepository,
) {
    @Test
    fun `when simulation is added then simulation is created`() {
        addNewSimulationUseCase.execute()
        val simulations = simulationRepository.findAll()

        assertThat(simulations).isNotEmpty()
        simulations.forEach { simulation ->
            assertThat(simulation.id).isNotNull()
            assertThat(simulation.status).isEqualTo(Status.NOT_RUNNING)
        }
    }
}
