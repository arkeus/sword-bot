package io.arkeus.sword.activity.battle

import io.arkeus.sword.data.Element
import io.arkeus.sword.user.item.Weapon
import io.arkeus.sword.user.item.InventoryItem

abstract trait Fightable {
	def hp: Int = 10 + stat("stamina") * 2
	def mp: Int = 5 + stat("spirit") * 2

	def name: String
	def level: Int
	def stat(stat: String): Int
	def damage: Int
	def armor: Int
	def weapon: InventoryItem
}