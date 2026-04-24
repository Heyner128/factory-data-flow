package me.heyner.manusim.core.domain

import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ManufacturingLineNodeRepository : ListCrudRepository<ManufacturingLineNode, ManufacturingLineNodeId>
