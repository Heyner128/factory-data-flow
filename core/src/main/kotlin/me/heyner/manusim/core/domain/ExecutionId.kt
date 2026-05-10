package me.heyner.manusim.core.domain

import jakarta.persistence.Embeddable

@Embeddable
data class ExecutionId(
    var simulation: SimulationId,
)
