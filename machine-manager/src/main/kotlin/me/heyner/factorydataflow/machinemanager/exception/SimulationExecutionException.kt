package me.heyner.factorydataflow.machinemanager.exception

class SimulationExecutionException(
    override val message: String,
) : RuntimeException(message)
