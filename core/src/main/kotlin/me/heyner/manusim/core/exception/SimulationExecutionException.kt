package me.heyner.manusim.core.exception

class SimulationExecutionException(
    override val message: String,
) : RuntimeException(message)
