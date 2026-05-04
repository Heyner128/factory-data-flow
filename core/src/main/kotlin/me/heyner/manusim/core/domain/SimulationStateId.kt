package me.heyner.manusim.core.domain

import jakarta.persistence.Embeddable

@Embeddable
data class SimulationStateId(
    var simulation: SimulationId,
)
