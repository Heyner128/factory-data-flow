package me.heyner.manusim.core.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import me.heyner.manusim.core.persistence.AbstractPersistableEntity
import java.time.OffsetDateTime

@Entity
@Table(name = "simulation_execution_log")
class SimulationExecutionLog(
    simulation: SimulationId,
) : AbstractPersistableEntity<SimulationExecutionLogId>() {
    @EmbeddedId
    @AttributeOverride(name = "simulation.id", column = Column(name = "simulation_id"))
    override var entityId: SimulationExecutionLogId = SimulationExecutionLogId(simulation)

    @Column(name = "start_date", nullable = false)
    var startDate: OffsetDateTime = OffsetDateTime.now()
}
