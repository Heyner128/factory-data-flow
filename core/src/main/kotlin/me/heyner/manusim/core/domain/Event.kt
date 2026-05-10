package me.heyner.manusim.core.domain

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import me.heyner.manusim.core.persistence.DurationInMillisecondsConverter
import java.time.Duration

@Embeddable
data class Event(
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    var type: EventType,
    @Column(name = "duration_in_ms")
    @Convert(converter = DurationInMillisecondsConverter::class)
    var duration: Duration,
)
