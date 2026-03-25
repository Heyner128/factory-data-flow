package me.heyner.factorydataflow.machinemanager.application

import me.heyner.factorydataflow.machinemanager.domain.ManufacturingLineId
import me.heyner.factorydataflow.machinemanager.domain.Simulation
import me.heyner.factorydataflow.machinemanager.domain.SimulationRepository
import me.heyner.factorydataflow.machinemanager.stereotype.UseCase

@UseCase
class AddNewSimulationUseCase(
    private val simulationRepository: SimulationRepository,
) {
    fun execute(manufacturingLineId: ManufacturingLineId) {
        simulationRepository.save(Simulation(manufacturingLineId))
    }
}
