package io.arkeus.sword.chat.command.impl

import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.command.router.Parameters
import io.arkeus.sword.user.item.ItemDatabase

object InventoryCommands {
	object Show extends Command {
		override def execute(user:SwordUser, params:Parameters) = {
			if (user.inventory.empty) {
				user.send("Your inventory is currently empty")
			} else {
				val inventory = user.inventory.all.zipWithIndex.map((zipped) => s"[${zipped._2}] ${zipped._1.name}").mkString(" ")
				user.send(s"[Inventory ${inventory.length} items] Use ''inventory ID'' to inspect an item.\n$inventory")
			}
		}
	}
	
	object Inspect extends Command {
		override def execute(user:SwordUser, params:Parameters) = {
			val item = user.inventory.get(params.int("id"))
			user.send(s"${item.name} [0 Slots]")
			//user.send(s"${item.itemtype} (${item.subinfo})")
		}
	}
	
	object Debug extends Command {
		override def execute(user:SwordUser, params:Parameters) = {
			user.inventory.add(ItemDatabase.Weapons(1))
		}
	}
}
