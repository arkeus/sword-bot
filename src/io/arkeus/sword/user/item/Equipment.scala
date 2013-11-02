package io.arkeus.sword.user.item

class Equipment {
	private var weaponItem: Option[Weapon] = None
	private var shieldItem: Option[Shield] = None
	private var armorItem: Option[Armor] = None

	def equip(item: Item) = {
		item match {
			case _: Weapon => weaponItem = Option(item.asInstanceOf[Weapon])
			case _: Shield => shieldItem = Option(item.asInstanceOf[Shield])
			case _: Armor => armorItem = Option(item.asInstanceOf[Armor])
			case _ => throw new IllegalArgumentException("Unknown item type: ${item.getClass.getName}")
		}
	}

	def weapon = weaponItem.getOrElse(ItemDatabase.Fists)
	def shield = shieldItem.getOrElse(ItemDatabase.Hand)
	def armor = armorItem.getOrElse(ItemDatabase.Skin)
}