package com.example.myapplication2.inheritanceKt

class Sphere(radius: Double) : Oval(radius) {
    override fun area(): Double {
        return 4 * radius * radius * 3.14
    }
}