package me.heyner.factorydataflow.machinemanager.domain

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name =  "manufacturing_line")
class ManufacturingLine {
    @EmbeddedId
    var id: ManufacturingLineId = ManufacturingLineId();
    
    @Column(name = "name", nullable = false)
    var name: String = ""
    
    constructor(name: String) {
        this.name = name
    }
}