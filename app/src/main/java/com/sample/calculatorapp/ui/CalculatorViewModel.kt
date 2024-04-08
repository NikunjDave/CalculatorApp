package com.sample.calculatorapp.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/***
 * Created by Nikunj Dave on 08/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 CalculatorApp.
 * All rights reserved.
 */
@HiltViewModel
class CalculatorViewModel @Inject constructor(): ViewModel(){

	fun intent(calculatorIntent: CalculatorIntent){
		when(calculatorIntent){
			is CalculatorIntent.Add -> add(calculatorIntent.a,calculatorIntent.b)
			is CalculatorIntent.Div -> divide(calculatorIntent.a,calculatorIntent.b)
			is CalculatorIntent.Multiply -> multiply(calculatorIntent.a,calculatorIntent.b)
			is CalculatorIntent.Sub -> subtract(calculatorIntent.a,calculatorIntent.b)
		}
	}

	val calculatorState =  MutableStateFlow<CalculatorState>(CalculatorState.Loading)

	private fun add(a: Double, b: Double){
		calculatorState.value = CalculatorState.Output(a.plus(b))
	}
	private fun subtract(a: Double, b: Double){
		calculatorState.value = CalculatorState.Output(a.minus(b))
	}
	private fun divide(a: Double, b: Double){
		calculatorState.value = CalculatorState.Output(a.div(b))
	}
	private fun multiply(a: Double, b: Double){
		calculatorState.value = CalculatorState.Output(a.times(b))
	}
}