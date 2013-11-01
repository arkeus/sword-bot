package io.arkeus.sword.user

import io.arkeus.sword.user.item.Equipment
import io.arkeus.sword.user.item.Weapon
import io.arkeus.sword.user.item.Armor
import io.arkeus.sword.user.item.Item
import org.jibble.pircbot.DccChat
import io.arkeus.sword.util.Logger

class SwordUser(val name:String) extends Logger {
	var chat:DccChat = null
	
	var experience = new Experience
	var equipment = new Equipment
	
	def equip(item:Item) = equipment.equip(item)
	def damage = equipment.weapon.damage
	def armor = equipment.armor.armor + equipment.shield.armor
	
	def send(message:String) = {
		if (chat != null) {
			chat.sendLine(message)
		} else {
			logger.warn(s"Attempted to send message to $this but no chat was open")
		}
	}
	
	override def toString:String = {
		return s"$name (Level ${experience.level} - ${experience.current}/${experience.max})"
	}
}