package me.heyner.manusim.core.application

import me.heyner.manusim.core.domain.Machine
import me.heyner.manusim.core.domain.MachineRepository
import me.heyner.manusim.core.stereotype.UseCase

@UseCase
class AddMachineUseCase(
    val machineRepository: MachineRepository,
) {
    fun execute() {
        machineRepository.save(Machine())
    }
}
