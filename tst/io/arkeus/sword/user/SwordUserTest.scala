package io.arkeus.sword.user

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers._
import org.junit.Test

class SwordUserTest {
	@Test
	def userProfileTest = {
		val user = new SwordUser("Bob")
		val expectedSelf = "[Bob] [Level 1 - 0/100] [Gold 0] [STR 10 | DEF 10 | WIS 10 | AGI 10 | STA 10 | SPI 10]"
		val expectedOther = "[Bob] [Level 1 - 0/100]"
		assertThat(user.profile(true), is(expectedSelf))
		assertThat(user.profile(false), is(expectedOther))
	}
}