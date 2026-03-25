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
@Table(name = "production_event")
class ProductionEvent(
    @Embedded
    @AttributeOverride(name = "id", column = Column(name = "machine_id", nullable = false))
    var machine: MachineId,
) : AbstractPersistableEntity<ProductionEventId>() {
    @EmbeddedId
    @AttributeOverride(name = "id", column = Column(name = "id", nullable = false))
    override var entityId: ProductionEventId = ProductionEventId()

    @Column(name = "start_date", nullable = false)
    var startDate: OffsetDateTime = OffsetDateTime.now()

    @Column(name = "quality", nullable = false)
    @Enumerated(EnumType.STRING)
    var quality: ProductionEventQuality = ProductionEventQuality.GOOD

    @Column(name = "end_date")
    var endDate: OffsetDateTime? = null
}
