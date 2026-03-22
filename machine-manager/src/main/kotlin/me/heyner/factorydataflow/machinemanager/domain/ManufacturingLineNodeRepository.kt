package me.heyner.factorydataflow.machinemanager.domain

import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ManufacturingLineNodeRepository : ListCrudRepository<ManufacturingLineNode, ManufacturingLineNodeId>
