package io.arkeus.sword.user.item

import io.arkeus.sword.data.Element

class Weapon(name: String, level: Int, properties: Map[String, Double] = null) extends Item(name, level, properties) {
	val damage = Weapon.damage(level)

	def element: Element.Value = Element.Physical

	override def itemtype = "Weapon"
	override def subinfo = s"$damage Damage"
	override def shortinfo = s"$damage"

	override def toString = s"''$name'' <:gray><0 slots><:> - ''$damage'' damage"
}

object Weapon {
	def damage = (level: Int) => 1 + level * 2
}