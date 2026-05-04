package me.heyner.manusim.core.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import me.heyner.manusim.core.persistence.AbstractPersistableEntity

@Entity
@Table(name = "simulation_state_event")
class SimulationStateEvent(
    simulationState: SimulationStateId,
) : AbstractPersistableEntity<SimulationStateEventId>() {
    @EmbeddedId
    @AttributeOverride(name = "simulationState.simulation.id", column = Column(name = "simulation_id"))
    override var entityId: SimulationStateEventId = SimulationStateEventId(simulationState)
}
