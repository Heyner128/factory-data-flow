package me.heyner.factorydataflow.machinemanager.application

import me.heyner.factorydataflow.machinemanager.domain.Simulation
import me.heyner.factorydataflow.machinemanager.domain.SimulationExecutionLog
import me.heyner.factorydataflow.machinemanager.domain.SimulationExecutionLogRepository
import me.heyner.factorydataflow.machinemanager.domain.SimulationId
import me.heyner.factorydataflow.machinemanager.domain.SimulationRepository
import me.heyner.factorydataflow.machinemanager.domain.SimulationStatus
import me.heyner.factorydataflow.machinemanager.exception.SimulationNotFoundException
import me.heyner.factorydataflow.machinemanager.stereotype.UseCase
import org.springframework.transaction.annotation.Transactional

@UseCase
class RunSimulationUseCase(
    private val simulationRepository: SimulationRepository,
    private val simulationExecutionLogRepository: SimulationExecutionLogRepository,
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
        val executionLog =
            simulationExecutionLogRepository.save(
                SimulationExecutionLog(simulation.id, SimulationStatus.STARTED),
            )
        simulation.start(executionLog.startDate)
    }
}
