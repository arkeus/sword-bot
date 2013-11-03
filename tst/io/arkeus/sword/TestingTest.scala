package io.arkeus.sword

import org.junit.Test
import io.arkeus.sword.activity.Stop
import scala.collection.mutable.ListBuffer
import io.arkeus.sword.activity.BattleActivity

class TestingTest {
	@Test
	def test: Unit = {
		val a = new BattleActivity(null).start
		Thread.sleep(5000)
		a ! Stop
	}
}