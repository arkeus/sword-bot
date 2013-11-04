package io.arkeus.sword.user.item

import io.arkeus.sword.data.Element

class Weapon(name: String, level: Int) extends Item(name, level) {
	val damage = 1 + level * 2
	
	def element: Element.Value = Element.Physical
	
	override def itemtype = "Weapon"
	override def subinfo = s"$damage Damage"
	override def shortinfo = s"$damage"
	
	override def toString = s"''$name'' <:gray><0 slots><:> - ''$damage'' damage"
}