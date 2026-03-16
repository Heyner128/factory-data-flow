package me.heyner.factorydataflow.machinemanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MachineManagerApplication

fun main(args: Array<String>) {
    runApplication<MachineManagerApplication>(*args)
}