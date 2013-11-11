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
import io.arkeus.sword.activity.Stop
import io.arkeus.sword.activity.Activity
import io.arkeus.sword.activity.BattleActivity
import io.arkeus.sword.activity.BattleActivity
import io.arkeus.sword.activity.BattleActivity
import io.arkeus.sword.activity.BattleActivity
import io.arkeus.sword.activity.BattleActivity
import io.arkeus.sword.activity.battle.Area
import io.arkeus.sword.activity.battle.Fightable
import com.twitter.json.Json
import io.arkeus.sword.SwordData
import org.jibble.pircbot.Colors
import io.arkeus.sword.user.item.ItemDatabase
import io.arkeus.sword.user.item.InventoryItem

class SwordUser(val name: String) extends Logger with Fightable {
	var chat: Chat = null
	var activity: Activity = null

	var administrator = false
	var gold = 0
	var stats = new Statistics
	var experience = new Experience
	var equipment = new Equipment
	var inventory = new Inventory

	def equip(item: InventoryItem) = {
		if (item != null) {
			val removedItem = equipment.equip(item) match {
				case Some(unequipped) =>
					inventory.add(unequipped); unequipped
				case None => null
			}
			inventory.remove(item)
			removedItem
		} else {
			null
		}
	}

	def stat(stat: String) = stats.get(stat)
	def weapon = equipment.weapon
	def damage = equipment.weapon.damage
	def armor = equipment.armor.armor + equipment.shield.armor
	def element = equipment.weapon.element
	def level = experience.level

	def gainGold(amount: Int) = gold += amount
	def gainExperience(amount: Int) = experience.gain(amount)

	def send(message: String) = {
		if (chat != null) {
			chat.send(message.colorize)
		} else {
			//logger.warn(s"Attempted to send message to $this but no chat was open")
		}
	}

	override def toString = name

	def profile(self: Boolean) = {
		val basicInfo = s"[$name]${if (administrator) "[<:red>admin<:>]"} [Level ${experience.level} - ${experience.current}/${experience.max}]"

		if (self) {
			val financeInfo = s"[Gold $gold]"
			val statProfile = Statistic.names.map(name => s"''${Statistic.shorten(name)}'' ${stat(name)}").mkString(" | ")
			val statInfo = s"[Stats $statProfile] {''Stat Points'' ${stats.points(level)}}"
			s"$basicInfo $financeInfo $statInfo"
		} else {
			s"$basicInfo"
		}
	}

	def battle(area: Area) = start[BattleActivity](List(area))

	def start[T <: Activity](args: List[Any])(implicit manifest: Manifest[T]) = {
		if (activity == null) {
			activity = manifest.erasure.getDeclaredConstructor(classOf[SwordUser], classOf[List[Any]]).newInstance(this, args).asInstanceOf[T]
			activity.start
		}
	}

	def stop = if (activity != null) activity ! Stop
	def finishActivity = activity = null
	def idle = activity == null

	def open(dccChat: DccChat) = {
		Users.setActive(this)
		logger.info(s"Initiating chat with $name")
		chat = new Chat(dccChat, this)
		chat.accept
		chat.start
		send("Welcome to {Sword Bot}, home of the {Sword Bot}, can I take your order?")
	}

	def close = {
		Users.setInactive(this)
		chat.close
		chat = null
	}

	def save = SwordData.saveUser(this)
	def load = SwordData.loadUser(this)
	
	def initialize = {
		val stick = ItemDatabase.byName("Wooden Stick").get.asInstanceOf[Weapon].toInventory
		inventory.add(stick)
		equip(stick)
	}

	def serialize = {
		Json.build(Map(
			"name" -> name,
			"gold" -> gold,
			"stats" -> stats.stats,
			"level" -> experience.level,
			"experience" -> experience.current,
			"inventory" -> inventory.serialize,
			"equipment" -> equipment.serialize)).toString
	}

	def unserialize(saved: String) = {
		val data = Json.parse(saved).asInstanceOf[Map[String, Any]]
		gold = data.get("gold").getOrElse(0).asInstanceOf[Int]
		stats.unserialize(data.get("stats").getOrElse(null).asInstanceOf[Map[String, Int]])
		val experienceLevel = data.get("level").getOrElse(experience.level).asInstanceOf[Int]
		val experienceCurrent = data.get("experience").getOrElse(experience.current).asInstanceOf[Int]
		experience.load(experienceLevel, experienceCurrent)
		inventory.unserialize(data.get("inventory").getOrElse(null).asInstanceOf[List[Map[String, Any]]])
		equipment.unserialize(data.get("equipment").getOrElse(null).asInstanceOf[List[Map[String, Any]]])
	}

	implicit class FormattedMessage(message: String) {
		implicit def colorize = Colorizer.colorize(message)
	}
}