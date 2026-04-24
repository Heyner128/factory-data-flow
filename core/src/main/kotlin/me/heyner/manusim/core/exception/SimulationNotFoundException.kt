package me.heyner.manusim.core.exception

class SimulationNotFoundException(
    override val message: String,
) : RuntimeException(message)
