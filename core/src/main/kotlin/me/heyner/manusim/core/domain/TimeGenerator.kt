package me.heyner.manusim.core.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.Duration
import java.util.Random

@Embeddable
data class TimeGenerator(
    @Column(name = "from", nullable = false)
    var from: Duration,
    @Column(name = "to", nullable = false)
    var to: Duration,
    @Transient
    val seed: Long? = null,
) {
    @Transient
    val random = Random()

    init {
        seed?.let { random.setSeed(it) }
    }

    fun generate(): Duration = Duration.ofMillis(random.nextLong(from.toMillis(), to.toMillis()))
}
