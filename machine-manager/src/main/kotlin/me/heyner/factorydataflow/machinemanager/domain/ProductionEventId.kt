package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.Embeddable

@Embeddable
data class ProductionEventId(
    var machine: MachineId,
    var simulation: SimulationId,
)
