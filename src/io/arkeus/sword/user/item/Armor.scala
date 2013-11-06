package io.arkeus.sword.user.item

class Armor(name: String, level: Int) extends Item(name, level) {
	val armor = Armor.armor(level)
	
	override def itemtype = "Armor"
	override def subinfo = s"$armor Armor"
	override def shortinfo = s"$armor"
	
	override def toString = s"''$name'' <:gray><0 slots><:> - ''$armor'' armor"
}

object Armor {
	def armor = (level: Int) => level * 2
}