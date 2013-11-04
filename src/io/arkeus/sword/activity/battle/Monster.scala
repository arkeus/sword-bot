package io.arkeus.sword.activity.battle

import io.arkeus.sword.data.Element
import io.arkeus.sword.user.item.Weapon

class Monster(val name: String, val level: Int) extends Fightable {
	def stat(stat:String):Int = level
	def damage:Int = level * 2
	def armor:Int = level * 4
	def weapon: Weapon = new Weapon("POOP", level)
}