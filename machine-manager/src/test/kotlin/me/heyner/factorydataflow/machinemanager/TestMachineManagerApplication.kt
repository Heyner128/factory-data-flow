package me.heyner.factorydataflow.machinemanager

import org.springframework.boot.fromApplication

fun main(args: Array<String>) {
    fromApplication<MachineManagerApplication>().run(*args)
}
