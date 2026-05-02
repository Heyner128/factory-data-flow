package me.heyner.manusim.core.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MachineRepository : JpaRepository<Machine, MachineId>
