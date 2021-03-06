package io.arkeus.sword.chat.command.impl

import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.command.router.Parameters

object ActivityCommands {
	object Stop extends Command(Command.HIDDEN | Command.IDLEABLE) {
		override def execute(user: SwordUser, params: Parameters) = {
			user.stop
		}
	}
}