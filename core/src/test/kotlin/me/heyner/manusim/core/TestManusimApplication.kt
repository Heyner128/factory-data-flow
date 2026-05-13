package me.heyner.manusim.core

import org.springframework.boot.fromApplication

fun main(args: Array<String>) {
    fromApplication<ManusimApplication>().run(*args)
}
