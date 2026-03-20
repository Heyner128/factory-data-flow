package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.Embeddable

@Embeddable
data class ManufacturingLineNodeId(
    val machine: MachineId,
    val manufacturingLine: ManufacturingLineId,
)
