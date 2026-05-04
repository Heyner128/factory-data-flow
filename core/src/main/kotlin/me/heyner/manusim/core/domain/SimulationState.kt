package me.heyner.manusim.core.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import me.heyner.manusim.core.persistence.AbstractPersistableEntity

@Entity
@Table(name = "simulation_state")
class SimulationState(
    simulation: SimulationId,
) : AbstractPersistableEntity<SimulationStateId>() {
    @EmbeddedId
    @AttributeOverride(name = "simulation.id", column = Column(name = "simulation_id"))
    override var entityId: SimulationStateId = SimulationStateId(simulation)

    @Column(name = "pieces_pending")
    var piecesPending: Long = 0

    @Column(name = "pieces_finished")
    var piecesFinished: Long = 0
}
