package me.heyner.manusim.core.application

import me.heyner.manusim.core.domain.MachineId
import me.heyner.manusim.core.domain.MachineRepository
import me.heyner.manusim.core.domain.SimulationId
import me.heyner.manusim.core.domain.SimulationRepository
import me.heyner.manusim.core.exception.MachineNotFoundException
import me.heyner.manusim.core.exception.SimulationNotFoundException
import me.heyner.manusim.core.stereotype.UseCase
import org.springframework.transaction.annotation.Transactional

@UseCase
class AddMachineToSimulationUseCase(
    val simulationRepository: SimulationRepository,
    val machineRepository: MachineRepository,
) {
    @Transactional
    fun execute(
        simulationId: SimulationId,
        machineId: MachineId,
    ) {
        val simulation =
            simulationRepository
                .findById(simulationId)
                .orElseThrow { SimulationNotFoundException(simulationId) }

        val machine = machineRepository.findById(machineId)

        if (!machine.isPresent) {
            throw MachineNotFoundException(machineId)
        }

        simulation.machine = machineId
    }
}
