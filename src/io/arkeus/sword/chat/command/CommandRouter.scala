package io.arkeus.sword.chat.command

import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.Message
import io.arkeus.sword.util.Logger
import io.arkeus.sword.chat.command.router.Router
import io.arkeus.sword.chat.command.impl.User

object CommandMap extends Logger {
	val commands = Map(
		"user" -> UserCommand
	)
	
	val router = new Router(List(
		("user $name:String", User.Profile),
		("user", User.Self)
	))
	
	def execute(user:SwordUser, message:Message) = {
		commands.get(message.command) match {
			case Some(command) => command.execute(user, message)
			case None => logger.info(s"User $user used invalid command '$message.command'")
		}
	}
}