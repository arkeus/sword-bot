package io.arkeus.sword.chat.command

import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.Message

abstract class Command {
	def execute(user:SwordUser, message:Message)
}