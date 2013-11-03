package io.arkeus.sword.user.item

class Equipment {
	private var weaponItem: Option[Weapon] = None
	private var shieldItem: Option[Shield] = None
	private var armorItem: Option[Armor] = None

	def equip(item: Item) = {
		item match {
			case _: Weapon => {
				val equipped = weaponItem
				weaponItem = Option(item.asInstanceOf[Weapon])
				equipped
			}
			case _: Shield => {
				val equipped = shieldItem
				shieldItem = Option(item.asInstanceOf[Shield])
				equipped
			}
			case _: Armor => {
				val equipped = armorItem
				armorItem = Option(item.asInstanceOf[Armor])
				equipped
			}
			case _ => throw new IllegalArgumentException("Unknown item type: ${item.getClass.getName}")
		}
	}
	
	def hasWeapon = weaponItem match { case Some(_) => true case None => false }
	def hasShield = shieldItem match { case Some(_) => true case None => false }
	def hasArmor = armorItem match { case Some(_) => true case None => false }

	def weapon = weaponItem.getOrElse(ItemDatabase.Fists)
	def shield = shieldItem.getOrElse(ItemDatabase.Hand)
	def armor = armorItem.getOrElse(ItemDatabase.Skin)
}