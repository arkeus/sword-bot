package io.arkeus.sword.chat.command.router.node

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.is
import org.junit.Test

class NodeTest {
	@Test
	def constantNodeTest = {
		val node = new ConstantNode("cow")
		assertThat(node.matches("cow"), is(true))
		assertThat(node.matches("sheep"), is(false))
		assertThat(node.matches("acow"), is(false))
		assertThat(node.matches("cowa"), is(false))
		assertThat(node.matches("cow "), is(false))
		assertThat(node.matches(" cow"), is(false))
	}
	
	@Test
	def variableNodeStringTest = {
		val node = new VariableNode("$name:String")
		assertThat(node.name, is("name"))
		assertThat(node.klass, is("String"))
		assertThat(node.matches("asd"), is(true))
		assertThat(node.matches(""), is(true))
		assertThat(node.matches("multiple tokens I guess"), is(true))
	}
	
	@Test
	def variableNodeIntTest = {
		val node = new VariableNode("$age:Int")
		assertThat(node.name, is("age"))
		assertThat(node.klass, is("Int"))
		assertThat(node.matches("123"), is(true))
		assertThat(node.matches("1"), is(true))
		assertThat(node.matches("-5"), is(true))
		assertThat(node.matches("1.1"), is(false))
		assertThat(node.matches("a3"), is(false))
		assertThat(node.matches("3a"), is(false))
	}
}