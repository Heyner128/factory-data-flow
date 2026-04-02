package me.heyner.factorydataflow.machinemanager.exception

class SimulationNotFoundException(
    override val message: String,
) : RuntimeException(message)
