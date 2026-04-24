package me.heyner.manusim.core.domain

import jakarta.persistence.Embeddable
import java.util.UUID

@Embeddable
data class ManufacturingLineId(
    var id: UUID = UUID.randomUUID(),
)
