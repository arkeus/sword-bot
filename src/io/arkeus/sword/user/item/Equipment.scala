package io.arkeus.sword.user.item

class Equipment {
	private var weaponItem: Option[InventoryItem] = None
	private var shieldItem: Option[InventoryItem] = None
	private var armorItem: Option[InventoryItem] = None

	def equip(item: InventoryItem) = {
		item.item match {
			case _: Weapon => {
				val equipped = weaponItem
				weaponItem = Option(item.asInstanceOf[InventoryItem])
				equipped
			}
			case _: Shield => {
				val equipped = shieldItem
				shieldItem = Option(item.asInstanceOf[InventoryItem])
				equipped
			}
			case _: Armor => {
				val equipped = armorItem
				armorItem = Option(item.asInstanceOf[InventoryItem])
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

	def serialize = List(weapon.serialize, shield.serialize, armor.serialize)
	def unserialize(data: List[Map[String, Any]]) = {
		if (data != null && data.length == 3) {
			val weaponData = data(0)
			if (weaponData("name") != "Fists") {
				unserializeEquip(weaponData)
			}

			val shieldData = data(1)
			if (shieldData("name") != "Hand") {
				unserializeEquip(shieldData)
			}

			val armorData = data(2)
			if (armorData("name") != "Skin") {
				unserializeEquip(armorData)
			}
		}
	}

	def unserializeEquip(data: Map[String, Any]) = {
		val item = ItemDatabase.byName(data("name").asInstanceOf[String]).getOrElse(null)
		if (item != null) {
			equip(item.toInventory)
		}
	}
}

object Equipment {
	val SLOTS = List("weapon", "shield", "armor")
}