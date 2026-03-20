package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "manufacturing_line_node")
class ManufacturingLineNode {
    @EmbeddedId
    @AttributeOverrides(
        AttributeOverride(name = "machine.id", column = Column(name = "machine_id", nullable = false)),
        AttributeOverride(
            name = "manufacturingLine.id",
            column = Column(name = "manufacturing_line_id", nullable = false),
        ),
    )
    lateinit var id: ManufacturingLineNodeId

    @Embedded
    @AttributeOverride(name = "id", column = Column(name = "next_machine_id", nullable = true))
    var nextMachine: MachineId? = null

    constructor(machine: MachineId, manufacturingLine: ManufacturingLineId) {
        this.id = ManufacturingLineNodeId(machine, manufacturingLine)
    }
}
