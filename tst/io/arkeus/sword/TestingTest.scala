package io.arkeus.sword

import org.junit.Test

class TestingTest {
	@Test
	def test: Unit = {
		println("GO")
		val add1 = addX(1) _
		println(add1(5))
		println(addX(3)(1))

		val add2 = timesX(2)
		println(add2(5))

		val add5 = plusX(5, _: Int)
		println(add5(5))
	}

	def addX(x: Int)(y: Int) = {
		x + y
	}

	def timesX(x: Int) = {
		(y: Int) => x + y
	}

	def plusX(x: Int, y: Int) = {
		x + y
	}
}