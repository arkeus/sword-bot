package io.arkeus.sword.user.item

class Shield(name: String, level: Int) extends Item(name, level) {
	val armor = level * 3
	
	override def itemtype = "Shield"
	override def subinfo = s"$armor Armor"
}