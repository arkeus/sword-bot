package io.arkeus.sword.user.item

import io.arkeus.sword.data.Element

object ItemDatabase {
	val Weapons = Array(
		new Weapon("Short Sword", 1),
		new Weapon("Long Sword", 2),
		new Weapon("Broad Sword", 3),
		new Weapon("Flame Blade", 4, Element.Fire),
		new Weapon("Splash Blade", 4, Element.Water),
		new Weapon("Spire Blade", 4, Element.Earth),
		new Weapon("Gust Blade", 4, Element.Air)
	)

	object Fists extends Weapon("Fists", 0)
	object Skin extends Armor("Skin", 0)
	object Hand extends Shield("Hand", 0)
	
	object Claws extends Weapon("Claws", 1)
}