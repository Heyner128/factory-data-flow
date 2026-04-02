package me.heyner.factorydataflow.machinemanager.application

import me.heyner.factorydataflow.machinemanager.domain.Simulation
import me.heyner.factorydataflow.machinemanager.domain.SimulationEvent
import me.heyner.factorydataflow.machinemanager.domain.SimulationEventRepository
import me.heyner.factorydataflow.machinemanager.domain.SimulationEventType
import me.heyner.factorydataflow.machinemanager.domain.SimulationId
import me.heyner.factorydataflow.machinemanager.domain.SimulationRepository
import me.heyner.factorydataflow.machinemanager.exception.SimulationExecutionException
import me.heyner.factorydataflow.machinemanager.exception.SimulationNotFoundException
import me.heyner.factorydataflow.machinemanager.stereotype.UseCase
import org.springframework.transaction.annotation.Transactional

@UseCase
class StartSimulationUseCase(
    private val simulationRepository: SimulationRepository,
    private val simulationEventRepository: SimulationEventRepository,
) {
    @Transactional
    fun execute(simulationId: SimulationId) {
        val simulation: Simulation =
            simulationRepository
                .findById(simulationId)
                .orElseThrow({
                    SimulationNotFoundException(
                        "The simulation $simulationId does not exist",
                    )
                })
        if (simulation.startDate != null) {
            throw SimulationExecutionException(
                "The simulation $simulationId has already been started",
            )
        }
        val startSimulationEvent = SimulationEvent(simulationId, SimulationEventType.START)
        simulationEventRepository.save(startSimulationEvent)
        simulation.startDate = startSimulationEvent.startDate
    }
}
