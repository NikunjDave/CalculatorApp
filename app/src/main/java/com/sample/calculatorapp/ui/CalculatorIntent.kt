package com.sample.calculatorapp.ui

/***
 * Created by Nikunj Dave on 08/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 CalculatorApp.
 * All rights reserved.
 */
sealed class CalculatorIntent {
	data class Add(val a: Double, val b: Double) : CalculatorIntent()
	data class Sub(val a: Double, val b: Double) : CalculatorIntent()
	data class Div(val a: Double, val b: Double) : CalculatorIntent()
	data class Multiply(val a: Double, val b: Double) : CalculatorIntent()
}