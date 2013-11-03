package io.arkeus.sword.user

import io.arkeus.sword.user.item.Equipment
import io.arkeus.sword.user.item.Weapon
import io.arkeus.sword.user.item.Armor
import io.arkeus.sword.user.item.Item
import org.jibble.pircbot.DccChat
import io.arkeus.sword.util.Logger
import io.arkeus.sword.user.UserHelper._
import io.arkeus.sword.user.message.Colorizer

class SwordUser(val name: String) extends Logger {
	var chat: DccChat = null

	var gold = 0
	var stats = new Statistics
	var experience = new Experience
	var equipment = new Equipment

	def equip(item: Item) = equipment.equip(item)
	def damage = equipment.weapon.damage
	def armor = equipment.armor.armor + equipment.shield.armor

	def send(message: String) = {
		if (chat != null) {
			chat.sendLine(message.colorize)
		} else {
			logger.warn(s"Attempted to send message to $this but no chat was open")
		}
	}

	override def toString = {
		s"$name (Level ${experience.level} - ${experience.current}/${experience.max})"
	}

	def profile(self: Boolean) = {
		val basicInfo = s"[$name] [Level ${experience.level} - ${experience.current}/${experience.max}]"
		val financeInfo = s"[Gold $gold]"
		val statInfo = s"[STATS $stats]"
		
		if (self) {
			s"$basicInfo $financeInfo $statInfo"
		} else {
			s"$basicInfo"
		}
	}
	
	implicit class FormattedMessage(message:String) {
		implicit def colorize = Colorizer.colorize(message)
	}
}