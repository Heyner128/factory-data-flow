package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "machine")
class Machine(
    @Column(name = "name", nullable = false)
    var name: String,
) {
    @EmbeddedId
    @AttributeOverride(name = "id", column = Column(name = "id", nullable = false))
    val id: MachineId = MachineId()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return id == (other as Machine).id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}
