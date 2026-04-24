package me.heyner.manusim.core.domain

import jakarta.persistence.Embeddable
import java.util.UUID

@Embeddable
data class MachineId(
    var id: UUID = UUID.randomUUID(),
)
