package me.heyner.manusim.core.domain

import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SimulationStateEventRepository : ListCrudRepository<SimulationStateEvent, SimulationStateEventId> {
    fun findAllByEntityIdSimulationState(simulationState: SimulationStateId): List<SimulationStateEvent>
}
