package me.heyner.manusim.core.domain

import jakarta.persistence.Embeddable
import java.util.UUID

@Embeddable
data class SimulationExecutionLogId(
    var id: UUID = UUID.randomUUID(),
)
