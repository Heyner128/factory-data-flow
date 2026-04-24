package me.heyner.manusim.core.application

import me.heyner.manusim.core.domain.Simulation
import me.heyner.manusim.core.domain.SimulationExecutionLog
import me.heyner.manusim.core.domain.SimulationExecutionLogRepository
import me.heyner.manusim.core.domain.SimulationId
import me.heyner.manusim.core.domain.SimulationRepository
import me.heyner.manusim.core.domain.SimulationStatus
import me.heyner.manusim.core.exception.SimulationNotFoundException
import me.heyner.manusim.core.stereotype.UseCase
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
