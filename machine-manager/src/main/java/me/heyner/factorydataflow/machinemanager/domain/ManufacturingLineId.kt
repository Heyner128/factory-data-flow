package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.util.UUID

@Embeddable
class ManufacturingLineId {
    @Column(name = "uuid", nullable = false)
    var uuid: UUID = UUID.randomUUID();
}