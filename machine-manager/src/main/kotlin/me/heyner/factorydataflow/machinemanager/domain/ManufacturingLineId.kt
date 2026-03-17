package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.util.UUID

@Embeddable
class ManufacturingLineId {
    @Column(name = "uuid", nullable = false)
    var uuid: UUID = UUID.randomUUID()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return uuid == (other as ManufacturingLineId).uuid
    }

    override fun hashCode(): Int = javaClass.hashCode()
}
