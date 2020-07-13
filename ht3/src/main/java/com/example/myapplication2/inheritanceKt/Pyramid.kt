package com.example.myapplication2.inheritanceKt

import kotlin.math.sqrt

class Pyramid(
        private var sidesCount: Int,
        private val sideOfSqrt: Double,
        side1: Double,
        side2: Double,
        side3: Double)
    : Triangle(side1, side2, side3) {

    override fun area(): Double {
        val perimeter: Double = (side1 + side2 + side3) / 2
        val sideOfPyramid = sqrt(perimeter * (perimeter - side1) * (perimeter - side2) * (perimeter - side3))
        return sideOfPyramid * sidesCount + sideOfSqrt * 2
    }
}