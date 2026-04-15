package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.Column
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
    @EmbeddedId
    @AttributeOverrides(
        AttributeOverride(name = "machine.id", column = Column(name = "machine_id", nullable = false)),
        AttributeOverride(
            name = "simulation.id",
            column = Column(name = "simulation_id", nullable = false),
        ),
    )
    override var entityId: ProductionEventId,
) : AbstractPersistableEntity<ProductionEventId>() {
    @Column(name = "start_date", nullable = false)
    var startDate: OffsetDateTime = OffsetDateTime.now()

    @Column(name = "quality", nullable = false)
    @Enumerated(EnumType.STRING)
    var quality: ProductionEventQuality = ProductionEventQuality.GOOD

    @Column(name = "end_date")
    var endDate: OffsetDateTime? = null
}
