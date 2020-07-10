package com.example.myapplication2.inheritanceKt

class Parallelepiped(
        private val line3: Double,
        line1: Double,
        line2: Double)
    : Rectangle(line1, line2) {

    override fun area(): Double {
        return 2 * (line1 * line2 + line2 * line3 + line1 * line3)
    }
}