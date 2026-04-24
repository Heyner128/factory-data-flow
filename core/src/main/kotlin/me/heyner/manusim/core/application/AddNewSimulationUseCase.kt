package me.heyner.manusim.core.application

import me.heyner.manusim.core.domain.ManufacturingLineId
import me.heyner.manusim.core.domain.Simulation
import me.heyner.manusim.core.domain.SimulationRepository
import me.heyner.manusim.core.stereotype.UseCase

@UseCase
class AddNewSimulationUseCase(
    private val simulationRepository: SimulationRepository,
) {
    fun execute(manufacturingLineId: ManufacturingLineId) {
        simulationRepository.save(Simulation(manufacturingLineId))
    }
}
