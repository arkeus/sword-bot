package io.arkeus.sword.chat.command.impl

import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.command.router.Parameters

object BattleCommands {
	object Test extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			user.battle
		}
	}
}