package io.arkeus.sword.user

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers._
import org.junit.Test
import com.twitter.json.Json

class SwordUserTest {
	@Test
	def userProfileTest = {
		val user = new SwordUser("Bob")
		val expectedSelf = "[Bob] [Level 1 - 0/10] [Gold 0] [STATS ''STR'' 5 | ''DEF'' 5 | ''WIS'' 5 | ''AGI'' 5 | ''STA'' 5 | ''SPI'' 5]"
		val expectedOther = "[Bob] [Level 1 - 0/10]"
		assertThat(user.profile(true), is(expectedSelf))
		assertThat(user.profile(false), is(expectedOther))
	}
	
	@Test
	def jsonTest = {
		val map = Map("bob" -> 1, "something" -> List(Map("a" -> 1), List(1, 2, 3)))
		//println(Json.build(map).toString)
		val parsed = Json.parse(Json.build(map).toString)
		//println(parsed)
	}
}