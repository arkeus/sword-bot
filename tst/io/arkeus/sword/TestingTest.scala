package io.arkeus.sword

import org.junit.Test
import io.arkeus.sword.activity.Stop
import scala.collection.mutable.ListBuffer
import io.arkeus.sword.activity.BattleActivity
import scala.collection.immutable.HashSet

class TestingTest {
	@Test
	def test: Unit = {
		val m1 = Map("a" -> 1, "b" -> 2)
		val m2 = Map("b" -> 1, "c" -> 2)
		val m3: Map[String, Int] = null
		
		println(keyset(m1) ++ keyset(m2) ++ keyset(m3))
	}
	
	def keyset(map: Map[String, Int]) = if (map == null) HashSet[String]() else map.keySet
}