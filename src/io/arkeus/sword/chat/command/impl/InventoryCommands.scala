package io.arkeus.sword.chat.command.impl

import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.command.router.Parameters
import io.arkeus.sword.user.item.ItemDatabase
import org.jibble.pircbot.Colors
import io.arkeus.sword.user.item.ItemInspector

object InventoryCommands {
	object Show extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			if (user.inventory.empty) {
				user.send("Your inventory is currently empty")
			} else {
				val inventory = user.inventory.all.zipWithIndex.map((zipped) => s"[${zipped._2 + 1}] ${zipped._1.name}").mkString(" ")
				user.send(s"[Inventory ${user.inventory.size} items] Use ''inventory ID'' to inspect an item.\n$inventory")
			}
		}
	}

	object Inspect extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			var id = params.int("id")
			if (id < 1 || id > user.inventory.size) {
				user.send(s"Invalid id ''$id'', use ''inventory'' command to view ids of items you own.")
			} else {
				val item = user.inventory.get(id - 1)
				val messages = ItemInspector.inspect(item)
				user.send(messages.mkString("\n"))
			}
		}
	}
	
	object Equipped extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			user.send("{''Equipped Items''} {Use ''equipped type'' (eg: ''equipped shield'') to inspect an item}")
			user.send("[Weapon] " + (if (user.equipment.hasWeapon) user.equipment.weapon else s"None (${user.equipment.weapon})"))
			user.send("[Shield] " + (if (user.equipment.hasShield) user.equipment.shield else s"None (${user.equipment.shield})"))
			user.send(" [Armor] " + (if (user.equipment.hasArmor) user.equipment.armor else s"None (${user.equipment.armor})"))
		}
	}
	
	object InspectEquipment extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			var slot = params.string("slot").toLowerCase()
			if (!List("weapon", "shield", "armor").contains(slot)) {
				user.send(s"Invalid slot ''$slot'', valid slots are ''weapon'', ''shield'', and ''armor''")
			} else {
				val item = slot match {
					case "weapon" => user.equipment.weapon
					case "shield" => user.equipment.shield
					case "armor" => user.equipment.armor
				}
				val messages = ItemInspector.inspect(item)
				user.send(messages.mkString("\n"))
			}
		}
	}
	
	object Equip extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			var id = params.int("id")
			if (id < 1 || id > user.inventory.size) {
				user.send(s"Invalid id ''$id'', use ''inventory'' command to view ids of items you own.")
			} else {
				val item = user.inventory.get(id - 1)
				val unequipped = user.equip(item)
				if (unequipped != null) {
					user.send(s"Unequipped {''$unequipped''}")
				}
				user.send(s"Equipped {''$item''}")
			}
		}
	}

	object Debug extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			user.inventory.add(ItemDatabase.Weapons(1))
		}
	}
}
