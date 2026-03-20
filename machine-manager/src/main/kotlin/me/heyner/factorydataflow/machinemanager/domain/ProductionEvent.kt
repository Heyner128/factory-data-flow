package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.OffsetDateTime

@Entity
@Table(name = "production_event")
class ProductionEvent {
    @EmbeddedId
    @AttributeOverride(name = "id", column = Column(name = "id", nullable = false))
    val id: ProductionEventId = ProductionEventId()

    @Embedded
    @AttributeOverride(name = "id", column = Column(name = "machine_id", nullable = false))
    lateinit var machine: MachineId

    @Column(name = "start_date", nullable = false)
    var startDate: OffsetDateTime = OffsetDateTime.now()

    @Column(name = "end_date")
    var endDate: OffsetDateTime? = null

    constructor(machine: MachineId, startDate: OffsetDateTime, endDate: OffsetDateTime?) {
        this.machine = machine
        this.startDate = startDate
        this.endDate = endDate
    }
}
