package me.heyner.manusim.core.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import me.heyner.manusim.core.persistence.AbstractPersistableEntity

@Entity
@Table(name = "machine")
class Machine(
    @Embedded
    @AttributeOverride(name = "from", column = Column(name = "time_generator_from", nullable = false))
    @AttributeOverride(name = "to", column = Column(name = "time_generator_to", nullable = false))
    var timeGenerator: TimeGenerator,
) : AbstractPersistableEntity<MachineId>() {
    @EmbeddedId
    @AttributeOverride(name = "id", column = Column(name = "id", nullable = false))
    override var entityId = MachineId()
}
