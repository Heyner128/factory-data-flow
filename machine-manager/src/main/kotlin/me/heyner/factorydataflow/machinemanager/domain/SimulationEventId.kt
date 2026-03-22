package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.Embeddable
import java.util.UUID

@Embeddable
data class SimulationEventId(
    val id: UUID = UUID.randomUUID(),
)
