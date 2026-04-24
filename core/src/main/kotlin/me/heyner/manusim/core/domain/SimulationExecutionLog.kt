package me.heyner.manusim.core.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import me.heyner.manusim.core.persistence.AbstractPersistableEntity
import java.time.OffsetDateTime

@Entity
@Table(name = "simulation_execution_log")
class SimulationExecutionLog(
    @Embedded
    @AttributeOverride(name = "id", column = Column(name = "simulation_id", nullable = false))
    var simulation: SimulationId,
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    var status: SimulationStatus,
) : AbstractPersistableEntity<SimulationExecutionLogId>() {
    @EmbeddedId
    @AttributeOverride(name = "id", column = Column(name = "id", nullable = false))
    override var entityId: SimulationExecutionLogId = SimulationExecutionLogId()

    @Column(name = "start_date", nullable = false)
    var startDate: OffsetDateTime = OffsetDateTime.now()

    @Column(name = "end_date")
    var endDate: OffsetDateTime? = null
}
