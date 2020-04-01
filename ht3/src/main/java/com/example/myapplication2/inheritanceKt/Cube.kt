package com.example.myapplication2.inheritanceKt

class Cube (private val areaSide: Double, side1: Double): Square(side1){
     override fun area():Double {
         return areaSide * 6
    }
    fun area(side1: Double): Double{
        val areas1de: Double = side1 * 2
        return areas1de * 6
    }
}