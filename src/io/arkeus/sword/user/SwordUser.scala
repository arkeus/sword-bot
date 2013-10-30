package io.arkeus.sword.user

import io.arkeus.sword.user.item.Equipment
import io.arkeus.sword.user.item.Weapon
import io.arkeus.sword.user.item.Armor
import io.arkeus.sword.user.item.Item

class SwordUser(val name:String) {
	var experience = new Experience
	var equipment = new Equipment
	
	def equip(item:Item) = equipment.equip(item)
	def damage = equipment.weapon.damage
	def armor = equipment.armor.armor + equipment.shield.armor
	
	override def toString:String = {
		return s"$name (Level ${experience.level} - ${experience.current}/${experience.max})"
	}
}