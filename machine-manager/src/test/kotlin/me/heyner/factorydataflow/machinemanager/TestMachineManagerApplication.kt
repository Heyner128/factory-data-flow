package me.heyner.factorydataflow.machinemanager

import org.springframework.boot.fromApplication
import org.springframework.boot.with

fun main(args: Array<String>) {
    fromApplication<MachineManagerApplication>().with(TestcontainersConfiguration::class).run(*args)
}
