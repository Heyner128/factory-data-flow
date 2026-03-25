package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.Embeddable

@Embeddable
data class ManufacturingLineNodeId(
    var machine: MachineId,
    var manufacturingLine: ManufacturingLineId,
)
