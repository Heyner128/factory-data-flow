package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.Embeddable
import java.util.UUID

@Embeddable
data class MachineId(
    var id: UUID = UUID.randomUUID(),
)
