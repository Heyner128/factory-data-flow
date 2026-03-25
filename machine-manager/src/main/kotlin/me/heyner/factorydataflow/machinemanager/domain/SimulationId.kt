package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.Embeddable
import java.util.UUID

@Embeddable
data class SimulationId(
    var id: UUID = UUID.randomUUID(),
)
