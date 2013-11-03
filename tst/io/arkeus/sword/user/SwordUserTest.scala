package io.arkeus.sword.user

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers._
import org.junit.Test

class SwordUserTest {
	@Test
	def userProfileTest = {
		val user = new SwordUser("Bob")
		val expectedSelf = "[Bob] [Level 1 - 0/100] [Gold 0] [STATS ''STR'' 5 | ''DEF'' 5 | ''WIS'' 5 | ''AGI'' 5 | ''STA'' 5 | ''SPI'' 5]"
		val expectedOther = "[Bob] [Level 1 - 0/100]"
		assertThat(user.profile(true), is(expectedSelf))
		assertThat(user.profile(false), is(expectedOther))
	}
}