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
class ProductionEvent(
    @Embedded
    @AttributeOverride(name = "id", column = Column(name = "machine_id", nullable = false))
    var machine: MachineId,
) {
    @EmbeddedId
    @AttributeOverride(name = "id", column = Column(name = "id", nullable = false))
    val id: ProductionEventId = ProductionEventId()

    @Column(name = "start_date", nullable = false)
    var startDate: OffsetDateTime = OffsetDateTime.now()

    @Column(name = "end_date")
    var endDate: OffsetDateTime? = null
}
