package io.arkeus.sword.activity.battle

class Area(val id: Int, val name: String, val monsters: List[Monster]) {
	val level = (monsters.map(_.level).sum / monsters.length.toDouble).ceil - 1
}