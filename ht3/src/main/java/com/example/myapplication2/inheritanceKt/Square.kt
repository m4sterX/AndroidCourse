package com.example.myapplication2.inheritanceKt

open class Square(public val side1: Double) {
    open fun area(): Double {
        return side1 * side1
    }
}