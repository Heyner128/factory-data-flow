package me.heyner.manusim.core.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.ElementCollection
import jakarta.persistence.Embedded
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table
import me.heyner.manusim.core.persistence.AbstractPersistableEntity
import me.heyner.manusim.core.persistence.DurationInMillisecondsConverter
import java.time.Duration
import java.util.Collections

@Entity
@Table(name = "execution")
class Execution(
    simulation: SimulationId,
) : AbstractPersistableEntity<ExecutionId>() {
    @EmbeddedId
    @AttributeOverride(name = "simulation.id", column = Column(name = "simulation_id"))
    override var entityId: ExecutionId = ExecutionId(simulation)

    @Embedded
    var state: State? = null

    @ElementCollection
    @CollectionTable(
        name = "execution_events",
        joinColumns = [JoinColumn(name = "simulation_id")],
    )
    var events: MutableList<Event> = Collections.synchronizedList(mutableListOf())

    @Column(name = "duration_in_ms")
    @Convert(converter = DurationInMillisecondsConverter::class)
    var duration: Duration? = null

    @Column(name = "duration_elapsed_in_ms", nullable = false)
    @Convert(converter = DurationInMillisecondsConverter::class)
    var durationElapsed: Duration = Duration.ZERO

    fun start(duration: Duration) {
        this.duration = duration
        state = State()
    }
}
