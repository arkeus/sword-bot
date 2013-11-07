package io.arkeus.sword.user.item

class Armor(name: String, level: Int, properties: Map[String, Double] = null) extends Item(name, level, properties) {
	val armor = Armor.armor(level)
	
	override def itemtype = "Armor"
	override def subinfo = s"$armor Armor"
	override def shortinfo = s"$armor"
	
	override def toString = s"''$name'' <:gray><0 slots><:> - ''$armor'' armor"
}

object Armor {
	def armor = (level: Int) => 4 + level * 2
}