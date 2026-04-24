package me.heyner.manusim.core.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import me.heyner.manusim.core.exception.SimulationExecutionException
import me.heyner.manusim.core.persistence.AbstractPersistableEntity
import java.time.OffsetDateTime
import java.util.LinkedList

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

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    var status: SimulationStatus = SimulationStatus.CREATED

    @Transient
    var eventList: List<ProductionEvent> = LinkedList()

    @Column(name = "end_date")
    var endDate: OffsetDateTime? = null

    fun start(date: OffsetDateTime) {
        if (startDate != null) {
            throw SimulationExecutionException(
                "The simulation $id has already been started",
            )
        }
        startDate = date
        status = SimulationStatus.STARTED
    }
}
