package me.heyner.manusim.core.application

import me.heyner.manusim.core.domain.Machine
import me.heyner.manusim.core.domain.MachineRepository
import me.heyner.manusim.core.domain.TimeGenerator
import me.heyner.manusim.core.stereotype.UseCase

@UseCase
class AddMachineUseCase(
    val machineRepository: MachineRepository,
) {
    fun execute(timeGenerator: TimeGenerator) {
        machineRepository.save(
            Machine(
                timeGenerator = timeGenerator,
            ),
        )
    }
}
