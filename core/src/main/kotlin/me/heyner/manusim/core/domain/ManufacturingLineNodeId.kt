package me.heyner.manusim.core.domain

import jakarta.persistence.Embeddable

@Embeddable
data class ManufacturingLineNodeId(
    var machine: MachineId,
    var manufacturingLine: ManufacturingLineId,
)
