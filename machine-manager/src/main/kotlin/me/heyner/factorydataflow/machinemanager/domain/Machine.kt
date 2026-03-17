package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "machine")
class Machine {
    @EmbeddedId
    var id: MachineId = MachineId()

    @Column(name = "name", nullable = false)
    var name: String = ""

    constructor(name: String) {
        this.name = name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return id == (other as Machine).id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}
