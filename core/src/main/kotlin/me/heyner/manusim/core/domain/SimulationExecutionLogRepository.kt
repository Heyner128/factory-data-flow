package me.heyner.manusim.core.domain

import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SimulationExecutionLogRepository : ListCrudRepository<SimulationExecutionLog, SimulationExecutionLogId> {
    fun findFirstBySimulation(simulation: SimulationId): SimulationExecutionLog?
}
