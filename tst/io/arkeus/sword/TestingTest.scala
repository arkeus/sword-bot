package io.arkeus.sword

import org.junit.Test

class TestingTest {
	@Test
	def test = {
		val tokens = "one two three message goes here".split(" ").toList
		val nodes = List(1, 2, 3)
		println(tokens.zip(nodes))
		println(nodes.zip(tokens))
	}
}