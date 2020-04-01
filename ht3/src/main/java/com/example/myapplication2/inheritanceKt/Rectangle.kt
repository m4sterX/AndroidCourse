package com.example.myapplication2.inheritanceKt

 open class Rectangle(val line1: Double, val line2: Double){
    open fun area(): Double{
    return line1 * line2
     }
 }