package io.arkeus.sword.chat.command.impl

import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.Message
import io.arkeus.sword.chat.command.router.Parameters
import io.arkeus.sword.user.Users

object UserCommands {
	object Self extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			user.send(user.profile(true))
		}
		
		override def help = "Views information about your character"
	}

	object Profile extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			val username = params.string("name")
			if (Users.exists(username)) {
				user.send(Users.find(username).profile(false))
			} else {
				user.send(s"Could not find user $username")
			}
		}
		
		override def help = "Views general information about another user's character"
	}
	
	object ActiveUsers extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			user.send("The follow users are currently active:")
			user.send(Users.activeUsers.map(_.name).mkString(" "))
		}
		
		override def help = "Shows the username of all active users"
	}
}