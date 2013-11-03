package io.arkeus.sword.user.item

class Weapon(name: String, level: Int, val element: Element.Value = Element.Physical) extends Item(name, level) {
	val damage = 1 + level * 2
	
	override def itemtype = "Weapon"
	override def subinfo = s"$damage Damage"
}