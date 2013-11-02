package io.arkeus.sword.chat.command.router

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers._
import org.junit.Test
import io.arkeus.sword.chat.command.router.parse._

class ParametersTest {
	@Test
	def parameterStringTest = {
		val params = new Parameters(Map[String, Any]("one" -> 1, "two" -> "two"))
		assertThat(params.toString, is("one => 1, two => two"))
	}
}