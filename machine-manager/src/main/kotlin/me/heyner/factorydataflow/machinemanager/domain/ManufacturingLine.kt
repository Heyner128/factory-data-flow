package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import me.heyner.factorydataflow.machinemanager.persistence.AbstractPersistableEntity

@Entity
@Table(name = "manufacturing_line")
class ManufacturingLine(
    @Column(name = "name", nullable = false)
    var name: String,
) : AbstractPersistableEntity<ManufacturingLineId>() {
    @EmbeddedId
    @AttributeOverride(name = "id", column = Column(name = "id", nullable = false))
    override var entityId: ManufacturingLineId = ManufacturingLineId()
}
