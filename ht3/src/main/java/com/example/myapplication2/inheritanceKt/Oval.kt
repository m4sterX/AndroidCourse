package com.example.myapplication2.inheritanceKt

 open class Oval(public val radius: Double) {
   open fun area(): Double{
    return radius * radius * 3.14
   }
}