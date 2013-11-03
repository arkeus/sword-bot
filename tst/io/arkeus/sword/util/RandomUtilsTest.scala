package io.arkeus.sword.util

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers._
import org.junit.Test

class RandomUtilsTest {
	@Test
	def basicTest = {
		assertThat(RandomUtils.random(-1, -1), is(-1))
		assertThat(RandomUtils.random(0, 0), is(0))
		assertThat(RandomUtils.random(1, 1), is(1))
		
		for (i <- 1 to 20) {
			val r = RandomUtils.random(2, 3)
			assertThat(r >= 2 && r <= 3, is(true))
		}
		
		for (i <- 1 to 20) {
			val r = RandomUtils.random(-4, -2)
			assertThat(r >= -4 && r <= -2, is(true))
		}
	}
}