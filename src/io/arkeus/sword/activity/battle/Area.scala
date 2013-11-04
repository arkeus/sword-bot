package io.arkeus.sword.activity.battle

import scala.util.Random
import io.arkeus.sword.util.RandomUtils

class Area(val id: Int, val name: String, val monsters: List[Monster]) {
	val level = ((monsters.map(_.level).sum / monsters.length.toDouble).ceil - 1).toInt
	
	def randomEnemy = monsters(RandomUtils.random(0, monsters.length - 1))
}