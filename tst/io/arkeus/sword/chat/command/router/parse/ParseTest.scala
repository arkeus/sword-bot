package io.arkeus.sword.chat.command.router.parse

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers._
import org.junit.Test
import io.arkeus.sword.chat.command.router.parse._

class ParseTest {
	@Test
	def parseStringTest = {
		assertThat(StringParser.parse("bob").get, is("bob"))
		assertThat(StringParser.parse("").get, is(""))
		assertThat(StringParser.parse(" ").get, is(" "))
		assertThat(StringParser.parse(null).get, is(nullValue))
	}

	@Test
	def parseIntTest = {
		assertThat(IntParser.parse("1").get, is(1))
		assertThat(IntParser.parse("1000").get, is(1000))
		assertThat(IntParser.parse("0").get, is(0))
		assertThat(IntParser.parse("-5").get, is(-5))

		assertThat(IntParser.parse(" ").isEmpty, is(true))
		assertThat(IntParser.parse("1.2").isEmpty, is(true))
		assertThat(IntParser.parse("bob").isEmpty, is(true))
		assertThat(IntParser.parse("a3").isEmpty, is(true))
		assertThat(IntParser.parse("3a").isEmpty, is(true))
	}

	@Test
	def parseDoubleTest = {
		assertThat(DoubleParser.parse("1.1").get, is(1.1))
		assertThat(DoubleParser.parse("1000.1000").get, is(1000.1000))
		assertThat(DoubleParser.parse("0").get, is(0.0))
		assertThat(DoubleParser.parse("-5").get, is(-5.0))
		assertThat(DoubleParser.parse("-5.0").get, is(-5.0))
		assertThat(DoubleParser.parse("-5.1").get, is(-5.1))

		assertThat(DoubleParser.parse(" ").isEmpty, is(true))
		assertThat(DoubleParser.parse("bob").isEmpty, is(true))
		assertThat(DoubleParser.parse("a3").isEmpty, is(true))
		assertThat(DoubleParser.parse("3a").isEmpty, is(true))
	}

	@Test
	def validateTest = {
		List("one", " ", "", "longer string").foreach(value => assertThat(StringParser.validate(value), is(true)))

		List("1", "1000", "0", "-5").foreach(value => assertThat(IntParser.validate(value), is(true)))
		List(" ", "1.2", "bob", "a3", "3a").foreach(value => assertThat(IntParser.validate(value), is(false)))

		List("1.1", "1000.1000", "0", "-5", "-5.0", "-5.1").foreach(value => assertThat(DoubleParser.validate(value), is(true)))
		List(" ", "bob", "a3", "3a").foreach(value => assertThat(DoubleParser.validate(value), is(false)))
	}

	@Test
	def parserSelectionTest = {
		val parsedString = TypeParser.parse("bob", "String")
		assertThat(parsedString.get.getClass.getName, is("java.lang.String"))
		assertThat(parsedString.get.asInstanceOf[String], is("bob"))

		val parsedInt = TypeParser.parse("1", "Int")
		assertThat(parsedInt.get.getClass.getName, is("java.lang.Integer"))
		assertThat(parsedInt.get.asInstanceOf[Int], is(1))

		val parsedDouble = TypeParser.parse("1.1", "Double")
		assertThat(parsedDouble.get.getClass.getName, is("java.lang.Double"))
		assertThat(parsedDouble.get.asInstanceOf[Double], is(1.1))
	}
}