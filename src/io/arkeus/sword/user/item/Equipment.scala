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

	def weapon = weaponItem.getOrElse(ItemDatabase.byName("Fists").get.asInstanceOf[Weapon])
	def shield = shieldItem.getOrElse(ItemDatabase.byName("Hand").get.asInstanceOf[Shield])
	def armor = armorItem.getOrElse(ItemDatabase.byName("Skin").get.asInstanceOf[Armor])
	
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
			equip(item)
		}
	}
}