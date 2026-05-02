package me.heyner.manusim.core.exception

import me.heyner.manusim.core.domain.MachineId

class MachineNotFoundException(
    machineId: MachineId,
) : RuntimeException("Machine \"$machineId\" not found.")
