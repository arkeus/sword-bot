package io.arkeus.sword.chat.command

import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.Message
import io.arkeus.sword.chat.command.router.Parameters

abstract class Command(val flags:Int = 0) {
	def execute(user: SwordUser, params: Parameters): Any
	def help = "No Help Found"
		
	def hidden = (flags & Command.HIDDEN) > 0
	def admin = (flags & Command.ADMIN) > 0
	def idleable = (flags & Command.IDLEABLE) > 0
}

object Command {
	val NONE = 0x0
	val HIDDEN = 0x1
	val ADMIN = 0x01
	val IDLEABLE = 0x001
}