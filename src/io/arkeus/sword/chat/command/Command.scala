package io.arkeus.sword.chat.command

import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.Message
import io.arkeus.sword.chat.command.router.Parameters

abstract class Command {
	def execute(user: SwordUser, params: Parameters): Any
}