package io.arkeus.sword.chat.command.impl

import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.command.router.Parameters
import io.arkeus.sword.user.item.ItemDatabase

object UselessCommands {
	object Look extends Command(true) {
		override def execute(user: SwordUser, params: Parameters) = {
			val direction = params.string("direction").toLowerCase()
			if (List("west", "east", "south", "north", "left", "right", "up", "down", "back", "forward", "around").contains(direction)) {
				user.send(s"You look ''$direction'' but don't see anything of interest...")
			} else {
				user.send("You don't know how to look that way!")
			}
		}
	}
	
	object Quit extends Command(true) {
		override def execute(user: SwordUser, params: Parameters) = {
			System.exit(0)
		}
	}
}