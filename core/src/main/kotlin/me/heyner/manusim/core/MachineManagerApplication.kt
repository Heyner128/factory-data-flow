package me.heyner.manusim.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MachineManagerApplication

fun main(args: Array<String>) {
    runApplication<me.heyner.manusim.core.MachineManagerApplication>(*args)
}
