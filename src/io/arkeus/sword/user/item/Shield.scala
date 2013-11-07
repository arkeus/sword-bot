package io.arkeus.sword.user.item

class Shield(name: String, level: Int, properties: Map[String, Double] = null) extends Item(name, level, properties) {
	val armor = Shield.armor(level)
	
	override def itemtype = "Shield"
	override def subinfo = s"$armor Armor"
	override def shortinfo = s"$armor"
	
	override def toString = s"''$name'' <:gray><0 slots><:> - ''$armor'' armor"
}

object Shield {
	val armor = (level: Int) => 6 + level * 3
}