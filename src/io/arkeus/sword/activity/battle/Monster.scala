package io.arkeus.sword.activity.battle

import io.arkeus.sword.data.Element
import io.arkeus.sword.user.item.Weapon
import io.arkeus.sword.user.item.Armor
import io.arkeus.sword.user.item.Shield
import io.arkeus.sword.user.item.InventoryItem

class Monster(val name: String, val level: Int) extends Fightable {
	def stat(stat:String):Int = level
	def damage:Int = level * 2
	def armor:Int = ((Shield.armor(level) + Armor.armor(level)) * 0.7).ceil.toInt
	def weapon: InventoryItem = new Weapon("cute little fluffy paws", level).toInventory
}