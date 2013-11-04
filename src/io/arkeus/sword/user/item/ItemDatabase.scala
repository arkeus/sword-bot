package io.arkeus.sword.user.item

import io.arkeus.sword.data.Element
import io.arkeus.sword.util.Database

object ItemDatabase extends Database[Item] {
	def build(items:List[Item]) = {
		var id = 1
		for (item <- items) {
			ids += item
			names.put(item.name, item)
			id += 1
		}
	}
	
	val tiers = List("Dwarven", "Scarab", "Golem", "Dryad", "Demon", "Elven", "Titan", "Dragon", "Valkyrie")
	val tierLevelSize = 20
	val tierItems = tiers.map(tier => {
		val level = tiers.indexOf(tier) * tierLevelSize
		List(
			// Tier Weapons
			createTierItem[Weapon](tier, "Short Dagger", 1),
			createTierItem[Weapon](tier, "Long Dagger", 3),
			createTierItem[Weapon](tier, "Short Sword", 6),
			createTierItem[Weapon](tier, "Long Sword", 10),
			createTierItem[Weapon](tier, "Broadsword", 14),
			createTierItem[Weapon](tier, "Bastard Sword", 18),
			createTierItem[Weapon](tier, "Light Axe", 1),
			createTierItem[Weapon](tier, "Heavy Axe", 3),
			createTierItem[Weapon](tier, "Halberd", 7),
			createTierItem[Weapon](tier, "Crescent Axe", 12),
			createTierItem[Weapon](tier, "Battle Axe", 17),
			// Tier Shields
			createTierItem[Shield](tier, "Buckler", 1),
			createTierItem[Shield](tier, "Targe Shield", 3),
			createTierItem[Shield](tier, "Heater Shield", 6),
			createTierItem[Shield](tier, "Kite Shield", 10),
			createTierItem[Shield](tier, "Tower Shield", 14),
			createTierItem[Shield](tier, "Aegis", 18),
			// Tier Armors
			createTierItem[Shield](tier, "Ring Mail", 1),
			createTierItem[Shield](tier, "Scale Mail", 3),
			createTierItem[Shield](tier, "Chainmail", 6),
			createTierItem[Shield](tier, "Splint Mail", 10),
			createTierItem[Shield](tier, "Banded Mail", 14),
			createTierItem[Shield](tier, "Plate Armor", 18)
		)
	}).flatten
	
	build(List(
		// Weapons
		new Weapon("Wooden Stick", 1),
		// Armor
		
		// Shields
		
		// Other
		new Weapon("Fists", 0),
		new Armor("Skin", 0),
		new Shield("Hand", 0),
		
		// Enemy Weapons
		new Weapon("Claws", 0)
	) ++ tierItems)
	
	private def createTierItem[T](tier: String, baseName: String, levelOffset: Int)(implicit manifest: Manifest[T]) = {
		val level = levelOffset + tiers.indexOf(tier) * 20
		val name = s"$tier $baseName"
		manifest.erasure.getDeclaredConstructor(classOf[String], classOf[Int]).newInstance(name, new Integer(level)).asInstanceOf[T]
	}
}