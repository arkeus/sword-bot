package io.arkeus.sword.chat.command.impl

import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.command.router.Parameters
import io.arkeus.sword.user.item.ItemDatabase
import org.jibble.pircbot.Colors

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
				user.send(s"[${item.name} - ${Colors.LIGHT_GRAY}0 Slots${Colors.NORMAL}]")
				user.send(s"${item.itemtype} (${item.subinfo})")
			}
		}
	}

	object Debug extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			user.inventory.add(ItemDatabase.Weapons(1))
		}
	}
}
