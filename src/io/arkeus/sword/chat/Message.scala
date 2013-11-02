package io.arkeus.sword.chat

import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.command.router.RouteHelper._

class Message(val raw:String) {
	val alias = raw.alias
	
	override def toString:String = raw
}