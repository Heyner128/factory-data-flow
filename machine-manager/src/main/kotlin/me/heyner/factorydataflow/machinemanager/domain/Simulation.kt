package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import me.heyner.factorydataflow.machinemanager.persistence.AbstractPersistableEntity
import java.time.OffsetDateTime

@Entity
@Table(name = "simulation")
class Simulation(
    @Embedded
    @AttributeOverride(name = "id", column = Column(name = "manufacturing_line_id", nullable = false))
    var manufacturingLine: ManufacturingLineId,
) : AbstractPersistableEntity<SimulationId>() {
    @EmbeddedId
    @AttributeOverride(name = "id", column = Column(name = "id", nullable = false))
    override var entityId: SimulationId = SimulationId()

    @Column(name = "start_date")
    var startDate: OffsetDateTime? = null

    @Column(name = "end_date")
    var endDate: OffsetDateTime? = null
}
