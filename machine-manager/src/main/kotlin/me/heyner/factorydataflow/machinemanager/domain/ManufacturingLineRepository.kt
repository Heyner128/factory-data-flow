package me.heyner.factorydataflow.machinemanager.domain

import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ManufacturingLineRepository : ListCrudRepository<ManufacturingLine, ManufacturingLineId>
