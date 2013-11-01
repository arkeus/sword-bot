package io.arkeus.sword.chat.command.impl

import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.Message

object User {
	object Self extends Command {
		override def execute(user:SwordUser, message:Message) = {
			user.send("Self profile")
		}
	}
	
	object Profile extends Command {
		override def execute(user:SwordUser, message:Message) = {
			user.send("Profile for other user")
		}
	}
}