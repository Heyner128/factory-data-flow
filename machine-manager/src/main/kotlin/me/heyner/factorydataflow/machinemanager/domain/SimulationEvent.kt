package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import me.heyner.factorydataflow.machinemanager.persistence.AbstractPersistableEntity
import java.time.OffsetDateTime

@Entity
@Table(name = "simulation_event")
class SimulationEvent(
    @Embedded
    @AttributeOverride(name = "id", column = Column(name = "simulation_id", nullable = false))
    var simulation: SimulationId,
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    var type: SimulationEventType,
) : AbstractPersistableEntity<SimulationEventId>() {
    @EmbeddedId
    @AttributeOverride(name = "id", column = Column(name = "id", nullable = false))
    override var entityId: SimulationEventId = SimulationEventId()

    @Column(name = "start_date", nullable = false)
    var startDate: OffsetDateTime = OffsetDateTime.now()

    @Column(name = "end_date")
    var endDate: OffsetDateTime? = null
}
