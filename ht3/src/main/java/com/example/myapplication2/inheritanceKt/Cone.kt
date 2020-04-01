package com.example.myapplication2.inheritanceKt

class Cone(private val radius: Double, private val edge: Double, side1: Double, side2: Double, side3: Double)
    : Triangle(side1, side2, side3) {

    override fun area(): Double {
        return 3.14 * radius * (radius + edge)
    }
}