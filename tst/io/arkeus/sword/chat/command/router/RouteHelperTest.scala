package io.arkeus.sword.chat.command.router

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers._
import org.junit.Test
import io.arkeus.sword.chat.command.router.RouteHelper._

class RouteHelperTest {
	@Test
	def routeHelperTest = {
		var string = "one\t\ttwo three\t \tfour"
		val tokens = string.tokenize
		assertThat(tokens(0), is("one"))
		assertThat(tokens(1), is("two"))
		assertThat(tokens(2), is("three"))
		assertThat(tokens(3), is("four"))
		assertThat(string.alias, is("one"))
	}
}