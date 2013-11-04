package io.arkeus.sword.user.item

class Shield(name: String, level: Int) extends Item(name, level) {
	val armor = Shield.armor(level)
	
	override def itemtype = "Shield"
	override def subinfo = s"$armor Armor"
	override def shortinfo = s"$armor"
	
	override def toString = s"''$name'' <:gray><0 slots><:> - ''$armor'' armor"
}

object Shield {
	val armor = (level: Int) => level * 3
}