package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.Embeddable
import java.util.UUID

@Embeddable
data class SimulationExecutionLogId(
    var id: UUID = UUID.randomUUID(),
)
