package io.arkeus.sword.user.item

import io.arkeus.sword.data.Element
import io.arkeus.sword.util.Database
import scala.collection.immutable.Map

object ItemDatabase extends Database[Item] {
	def build(items: List[Item]) = {
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
			createTierItem[Armor](tier, "Ring Mail", 1),
			createTierItem[Armor](tier, "Scale Mail", 3),
			createTierItem[Armor](tier, "Chainmail", 6),
			createTierItem[Armor](tier, "Splint Mail", 10),
			createTierItem[Armor](tier, "Banded Mail", 14),
			createTierItem[Armor](tier, "Plate Armor", 18))
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
		new Weapon("Claws", 0)) ++ tierItems)

	private def createTierItem[T](tier: String, baseName: String, levelOffset: Int)(implicit manifest: Manifest[T]): T = {
		createTierItem(tier, baseName, levelOffset, Map[String, Double]())
	}
	
	private def createTierItem[T](tier: String, baseName: String, levelOffset: Int, properties: Map[String, Double])(implicit manifest: Manifest[T]): T = {
		val level = levelOffset + tiers.indexOf(tier) * 20
		val name = s"$tier $baseName"
		println(manifest.erasure.getDeclaredConstructors()(0).toString())
		println(manifest.erasure.getDeclaredConstructor(classOf[String], classOf[Int], classOf[Map[String, Double]]))
		manifest.erasure.getDeclaredConstructor(classOf[String], classOf[Int], classOf[Map[String, Double]]).newInstance(name, new Integer(level), properties).asInstanceOf[T]
	}

	val Fists = new InventoryItem(byName("Fists").get)
	val Skin = new InventoryItem(byName("Skin").get)
	val Hand = new InventoryItem(byName("Hand").get)
}