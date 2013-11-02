package io.arkeus.sword.chat.command.router

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers._
import org.junit.Test

class RouteTest {
	@Test
	def simpleRouteCreationTest = {
		val route = new Route("simple", null)
		assertThat(route.path, is("simple"))
		assertThat(route.command, is(nullValue()))
		assertThat(route.score, is(1))
		assertThat(route.alias, is("simple"))
		assertThat(route.nodes.length, is(1))
	}
	
	@Test
	def complexRouteCreationTest = {
		val route = new Route("i have $num:Int $animal:String", null)
		assertThat(route.path, is("i have $num:Int $animal:String"))
		assertThat(route.command, is(nullValue()))
		assertThat(route.score, is(2))
		assertThat(route.alias, is("i"))
		assertThat(route.nodes.length, is(4))
	}
	
	@Test
	def constantRouteMatchingTest = {
		val route = new Route("one two three", null)
		assertThat(route.matches("one two three"), is(true))
		assertThat(route.matches("one two three message afterwards"), is(true))
		assertThat(route.matches("one two"), is(false))
		assertThat(route.matches("two three"), is(false))
	}
	
	@Test
	def variableRouteMatchingTest = {
		val route = new Route("buy $num:Int $item:String", null)
		assertThat(route.matches("buy 1 sword"), is(true))
		assertThat(route.matches("buy 1 get one free"), is(true))
		assertThat(route.matches("buy 27 cats"), is(true))
		assertThat(route.matches("buy two swords"), is(false))
		assertThat(route.matches("buy 1"), is(false))
		assertThat(route.matches("buy"), is(false))
	}
	
	@Test
	def parameterizeRouteTest = {
		val route = new Route("buy $num:Int $item:String", null)
		val params = route.parameterize("buy 4 sword")
		assertThat(params.int("num"), is(4))
		assertThat(params.string("item"), is("sword"))
	}
	
	@Test
	def overloadedRouteTest = {
		val route = new Route("buy $num:Int $item:String", null)
		val route2 = new Route("buy $num:Int $item-id:Int", null)
		
		assertThat(route.matches("buy 1 sword"), is(true))
		assertThat(route2.matches("buy 1 sword"), is(false))
		
		assertThat(route.matches("buy 1 5"), is(true))
		assertThat(route2.matches("buy 1 5"), is(true))
	}
}