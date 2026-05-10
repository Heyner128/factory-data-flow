package me.heyner.manusim.core.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.Duration

@Embeddable
data class TimeGenerator(
    @Column(name = "from", nullable = false)
    var from: Duration,
    @Column(name = "to", nullable = false)
    var to: Duration,
)
