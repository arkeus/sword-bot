package io.arkeus.sword.user

import io.arkeus.sword.user.item.Equipment
import io.arkeus.sword.user.item.Weapon
import io.arkeus.sword.user.item.Armor
import io.arkeus.sword.user.item.Item
import org.jibble.pircbot.DccChat
import io.arkeus.sword.util.Logger
import io.arkeus.sword.user.UserHelper._
import io.arkeus.sword.user.message.Colorizer
import io.arkeus.sword.user.item.Inventory
import io.arkeus.sword.chat.Chat

class SwordUser(val name: String) extends Logger {
	var chat: Chat = null

	var gold = 0
	var stats = new Statistics
	var experience = new Experience
	var equipment = new Equipment
	var inventory = new Inventory

	def equip(item: Item) = {
		val removedItem = equipment.equip(item) match {
			case Some(unequipped) => inventory.add(unequipped); unequipped
			case None => null
		}
		inventory.remove(item)
		removedItem
	}
	
	def damage = equipment.weapon.damage
	def armor = equipment.armor.armor + equipment.shield.armor

	def send(message: String) = {
		if (chat != null) {
			chat.send(message.colorize)
		} else {
			logger.warn(s"Attempted to send message to $this but no chat was open")
		}
	}

	override def toString = name

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
	
	def open(dccChat: DccChat) = {
		logger.info(s"Initiating chat with $name")
		chat = new Chat(dccChat, this)
		chat.accept
		chat.start
		send("Welcome to {Sword Bot}, home of the {Sword Bot}, can I take your order?")
	}
	
	def close = {
		chat.close
		chat = null
	}
	
	implicit class FormattedMessage(message:String) {
		implicit def colorize = Colorizer.colorize(message)
	}
}