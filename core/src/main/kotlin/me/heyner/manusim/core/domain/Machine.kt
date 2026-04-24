package me.heyner.manusim.core.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import me.heyner.manusim.core.persistence.AbstractPersistableEntity

@Entity
@Table(name = "machine")
class Machine(
    @Column(name = "name", nullable = false)
    var name: String,
) : AbstractPersistableEntity<MachineId>() {
    @EmbeddedId
    @AttributeOverride(name = "id", column = Column(name = "id", nullable = false))
    override var entityId: MachineId = MachineId()
}
