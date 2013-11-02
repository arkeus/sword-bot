package io.arkeus.sword.chat.command.impl

import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.Message
import io.arkeus.sword.chat.command.router.Parameters

object User {
	object Self extends Command {
		override def execute(user:SwordUser, params:Parameters) = {
			user.send("Self profile")
		}
	}
	
	object Profile extends Command {
		override def execute(user:SwordUser, params:Parameters) = {
			user.send(s"Profile for user '${params.string("name")}'")
		}
	}
}