package com.sample.calculatorapp.ui

/***
 * Created by Nikunj Dave on 08/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 CalculatorApp.
 * All rights reserved.
 */
sealed class CalculatorState {
	object Loading : CalculatorState()
	data class Output(val output: Double) : CalculatorState()
	data class Error(val error : String) : CalculatorState()
}