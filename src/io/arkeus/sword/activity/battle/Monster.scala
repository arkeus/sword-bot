package io.arkeus.sword.activity.battle

import io.arkeus.sword.data.Element

class Monster(val id: Int, val name: String, val level: Int, val element: Element.Value = Element.Physical) extends Fightable {
	def stat(stat:String):Int = level
	def damage:Int = level * 2
	def armor:Int = level * 4
}