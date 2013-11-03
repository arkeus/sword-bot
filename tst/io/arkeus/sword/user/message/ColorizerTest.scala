package io.arkeus.sword.user.message

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers._
import org.junit.Test
import org.jibble.pircbot.Colors

class ColorizerTest {
	@Test
	def noColorTest = {
		val message = "simple message here"
		val colorized = Colorizer.colorize(message)
		assertThat(colorized, is("simple message here"))
	}
	
	@Test
	def tagTest = {
		val message = "this ''is'' simple"
		val colorized = Colorizer.colorize(message)
		assertThat(colorized, is("this " + Colors.BOLD + "is" + Colors.BOLD + " simple"))
	}
}