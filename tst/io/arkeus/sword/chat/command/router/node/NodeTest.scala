package io.arkeus.sword.chat.command.router.node

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.is
import org.junit.Assert.assertEquals
import org.junit.Test
import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.Message

class NodeTest {
	@Test
	def parseSinglePath = {
		val (token, rest) = (new Node).splitPath("one")
		assertThat(token, is("one"))
		assertThat(rest, is(""))
	}
	
	@Test
	def parseLongPath = {
		val (token, rest) = (new Node).splitPath("one two three")
		assertThat(token, is("one"))
		assertThat(rest, is("two three"))
	}
	
	@Test
	def parseEmptyPath = {
		val (token, rest) = (new Node).splitPath("")
		assertThat(token, is(""))
		assertThat(rest, is(""))
	}
	
	@Test
	def createEmptyNode = {
		val node = new Node
		assertThat(node.children.size, is(0))
		assertThat(node.size, is(1))
	}
	
	@Test
	def createSingleChildNode = {
		val node = new Node
		node.add("simple")
		
		assertThat(node.children.size, is(1))
		assertThat(node.size, is(2))
	}
	
	@Test
	def createMultiChildNode = {
		val node = new Node
		node.add("one")
		node.add("two")
		node.add("three")
		
		assertThat(node.children.size, is(3))
		assertThat(node.size, is(4))
	}
	
	@Test
	def createShallowTreeFromRoot = {
		val node = new RootNode
		node.build(List(("one", null), ("two", null), ("three", null)))
		
		assertThat(node.children.size, is(3))
		assertThat(node.size, is(4))
	}
	
	@Test
	def createDeepLinearTree = {
		val node = new RootNode
		node.build(List(("one two three", null)))
		
		assertThat(node.children.size, is(1))
		assertThat(node.size, is(4))
	}
	
	@Test
	def createSimpleDeepTreeFromRootDeepToShallow = {
		val node = new RootNode
		node.build(List(
			("one two", null),
			("one", null)
		))
		
		println(node)
		assertThat(node.children.size, is(1))
		assertThat(node.size, is(3))
	}
	
	@Test
	def createDeepTreeFromRootDeepToShallow = {
		val node = new RootNode
		node.build(List(
			("one two three", null),
			("one two", null),
			("one", null),
			("two three", null),
			("two", null)
		))
		
		println(node)
		assertThat(node.children.size, is(2))
		assertThat(node.size, is(6))
	}
	
	@Test
	def basicRouting = {
		val node = new RootNode
		node.build(List(
			command("one two three"),
			command("one two"),
			command("one"),
			command("two three"),
			command("two")
		))
		
		assertThat(node.children.size, is(2))
		assertThat(node.size, is(6))
		
		List("one", "two", "one two", "two three", "one two three").foreach((name:String) => assertThat(node.route(name).asInstanceOf[TestCommand].value, is(name)))
	}
	
	private def command(name:String):(String, Command) = {
		return (name, new TestCommand(name))
	}
	
	private class TestCommand(val value:String) extends Command {
		override def execute(user:SwordUser, message:Message) = {}
	}
}