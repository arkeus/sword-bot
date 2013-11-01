package io.arkeus.sword.chat

import io.arkeus.sword.user.SwordUser

class Message(val raw:String) {
	val command = raw.split(" ")(0)
	
	override def toString:String = raw
}