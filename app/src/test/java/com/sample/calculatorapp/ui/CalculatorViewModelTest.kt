package com.sample.calculatorapp.ui

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

/***
 * Created by Nikunj Dave on 08/04/24
 *
 * Description :
 *
 * Copyright(c) 2024 CalculatorApp.
 * All rights reserved.
 */

class CalculatorViewModelTest {

	private lateinit var viewModel: CalculatorViewModel

	@BeforeEach
	fun setUp() {
		viewModel = CalculatorViewModel()
	}

	@Test
	fun testAdd() = runBlocking {
		viewModel.intent(CalculatorIntent.Add(3.0, 2.0))
		val state = viewModel.calculatorState.first()
		assertEquals(CalculatorState.Output(5.0), state)
	}

	@Test
	fun testSubtract() = runBlocking {
		viewModel.intent(CalculatorIntent.Sub(5.0, 2.0))
		val state = viewModel.calculatorState.first()
		assertEquals(CalculatorState.Output(3.0), state)
	}

	@Test
	fun testMultiply() = runBlocking {
		viewModel.intent(CalculatorIntent.Multiply(3.0, 2.0))
		val state = viewModel.calculatorState.first()
		assertEquals(CalculatorState.Output(6.0), state)
	}

	@Test
	fun testDivide() = runBlocking {
		viewModel.intent(CalculatorIntent.Div(6.0, 2.0))
		val state = viewModel.calculatorState.first()
		assertEquals(CalculatorState.Output(3.0), state)
	}

}