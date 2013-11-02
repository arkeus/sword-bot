package io.arkeus.sword.chat

import org.jibble.pircbot.DccChat

object ChatHandler {
	def accept(chat: DccChat) = {
		new Chat(chat).start
	}
}