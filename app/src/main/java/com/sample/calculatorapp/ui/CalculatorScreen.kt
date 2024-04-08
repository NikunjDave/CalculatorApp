package com.sample.calculatorapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/***
 * Created by Nikunj Dave on 08/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 CalculatorApp.
 * All rights reserved.
 */
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CalculatorScreen() {
	val calculatorViewModel: CalculatorViewModel = hiltViewModel()
	val coroutineScope = rememberCoroutineScope()

	val output = remember { mutableStateOf("") }

	coroutineScope.launch {
		calculatorViewModel.calculatorState.collectLatest { state ->
			when (state) {
				is CalculatorState.Loading -> {
					println("Loading")
					output.value = ""
				}

				is CalculatorState.Error -> {
					println("Error")
					output.value = state.error
				}

				is CalculatorState.Output -> {
					println("Loading")
					output.value = state.output.toString()
				}
			}
		}

	}


	Column(
		modifier = Modifier.padding(16.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {

		val first = remember { mutableDoubleStateOf(0.0) }
		val second = remember { mutableDoubleStateOf(0.0) }

		NumberInputField(value = first.doubleValue.toString()) { newValue ->
			first.doubleValue = newValue.toDouble()
		}

		NumberInputField(value = second.doubleValue.toString()) { newValue ->
			second.doubleValue = newValue.toDouble()
		}


		Row(modifier = Modifier.padding(16.dp)) {
			Button(onClick = {
				calculatorViewModel.intent(CalculatorIntent.Add(first.doubleValue, second.doubleValue))
			}) {
				Text("Add")
			}


			Button(onClick = {
				calculatorViewModel.intent(CalculatorIntent.Sub(first.doubleValue, second.doubleValue))
			}) {
				Text("Subtract")
			}
		}


		Row(modifier = Modifier.padding(16.dp)) {
			Button(onClick = {
				calculatorViewModel.intent(CalculatorIntent.Div(first.doubleValue, second.doubleValue))
			}) {
				Text("Divide")
			}


			Button(onClick = {
				calculatorViewModel.intent(CalculatorIntent.Multiply(first.doubleValue, second.doubleValue))
			}) {
				Text("Multiply")
			}
		}

		Text(text = "Output is ${output.value}")

	}

}


@Composable
fun NumberInputField(
	value: String,
	onValueChange: (String) -> Unit
) {
	TextField(
		value = value,
		onValueChange = onValueChange,
		keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
	)
}