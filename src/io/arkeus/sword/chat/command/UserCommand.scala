package io.arkeus.sword.chat.command

import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.Message

object UserCommand extends Command {
	override def execute(user:SwordUser, message:Message) = {
		user.send("You just user'd me!")
	}
}