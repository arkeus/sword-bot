package io.arkeus.sword.user.item

import io.arkeus.sword.data.Element
import io.arkeus.sword.util.Database

object ItemDatabase extends Database[Item] {
	def build(items:Array[Item]) = {
		var id = 1
		for (item <- items) {
			ids += item
			names.put(item.name, item)
			id += 1
		}
	}
	
	build(Array(
		// Weapons
		new Weapon("Short Sword", 1, Element.Physical),
		new Weapon("Long Sword", 2, Element.Physical),
		new Weapon("Broad Sword", 3, Element.Physical),
		new Weapon("Flame Blade", 4, Element.Fire),
		new Weapon("Splash Blade", 4, Element.Water),
		new Weapon("Spire Blade", 4, Element.Earth),
		new Weapon("Gust Blade", 4, Element.Air),
		// Armor
		
		// Shields
		
		// Other
		new Weapon("Fists", 0),
		new Armor("Skin", 0),
		new Shield("Hand", 0),
		
		// Enemy Weapons
		new Weapon("Claws", 1)
	))
}