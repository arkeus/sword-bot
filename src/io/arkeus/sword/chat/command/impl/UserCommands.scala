package io.arkeus.sword.chat.command.impl

import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.Message
import io.arkeus.sword.chat.command.router.Parameters
import io.arkeus.sword.user.Users

object UserCommands {
	object Self extends Command {
		override def execute(user:SwordUser, params:Parameters) = {
			user.send(user.profile(true))
		}
	}
	
	object Profile extends Command {
		override def execute(user:SwordUser, params:Parameters) = {
			val username = params.string("name")
			if (Users.exists(username)) {
				user.send(Users.find(username).profile(false))
			} else {
				user.send(s"Could not find user $username")
			}
		}
	}
}