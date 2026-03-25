package me.heyner.factorydataflow.machinemanager.application

import me.heyner.factorydataflow.machinemanager.domain.SimulationEvent
import me.heyner.factorydataflow.machinemanager.domain.SimulationEventRepository
import me.heyner.factorydataflow.machinemanager.domain.SimulationEventType
import me.heyner.factorydataflow.machinemanager.domain.SimulationId
import me.heyner.factorydataflow.machinemanager.domain.SimulationRepository
import me.heyner.factorydataflow.machinemanager.stereotype.UseCase

@UseCase
class StartSimulationUseCase(
    private val simulationRepository: SimulationRepository,
    private val simulationEventRepository: SimulationEventRepository,
) {
    fun execute(simulationId: SimulationId) {
        val startSimulationEvent = SimulationEvent(simulationId, SimulationEventType.START)
        simulationEventRepository.save(startSimulationEvent)
    }
}
