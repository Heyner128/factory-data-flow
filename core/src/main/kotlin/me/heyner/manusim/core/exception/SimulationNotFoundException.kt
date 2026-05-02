package me.heyner.manusim.core.exception

import me.heyner.manusim.core.domain.SimulationId

class SimulationNotFoundException(
    simulationId: SimulationId,
) : RuntimeException("Simulation \"$simulationId\" not found.")
