package com.example.myapplication2.inheritanceKt

import kotlin.math.sqrt

open class Triangle(public val side1: Double, public val side2: Double, public val side3: Double) {
    open fun area(): Double {
        val perimeter: Double = (side1 + side2 + side3) / 2
        return sqrt(perimeter * (perimeter - side1) * (perimeter - side2) * (perimeter - side3))
    }
}