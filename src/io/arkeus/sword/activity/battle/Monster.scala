package io.arkeus.sword.activity.battle

import io.arkeus.sword.data.Element
import io.arkeus.sword.user.item.Weapon
import io.arkeus.sword.user.item.Armor
import io.arkeus.sword.user.item.Shield
import io.arkeus.sword.user.item.InventoryItem

class Monster(val name: String, val level: Int) extends Fightable {
	val statLevel = Math.max(1, level - 4)
	def stat(stat:String):Int = (3 * statLevel / 5).ceil.toInt
	def damage:Int = (Weapon.damage(statLevel) * 0.7).ceil.toInt
	def armor:Int = ((Shield.armor(statLevel) + Armor.armor(statLevel)) * 0.7).ceil.toInt
	def weapon: InventoryItem = new Weapon("cute little fluffy paws", level).toInventory
}