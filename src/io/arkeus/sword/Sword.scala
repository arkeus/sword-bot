package io.arkeus.sword

import org.jibble.pircbot.PircBot
import org.apache.logging.log4j.LogManager
import io.arkeus.sword.config.Config
import org.jibble.pircbot.NickAlreadyInUseException
import io.arkeus.sword.user.Users
import org.jibble.pircbot.DccChat
import io.arkeus.sword.chat.ChatHandler

class Sword(val config:Config) extends PircBot {
	val logger = LogManager.getLogger(this.getClass)
	
	def initialize = {
		logger.info(s"Connecting to ${config.server}")
		
		setLogin(config.login)
		setVerbose(config.verbose)
		setName(config.nick)
		setVersion(config.login)
		
		connect(config.server)
	}
	
	override protected def onConnect = joinChannel(config.channel)
	
	override protected def onDisconnect = {
		logger.warn("Sword has been disconnected")
		
		while (!isConnected()) {
			try {
				logger.info("Attempting to reconnect...")
				reconnect()
			}
		}
	}
	
	override protected def onMessage(channel:String, sender:String, login:String, hostname:String, message:String) = {
		val user = Users.find(sender)
		user.experience.gain(50)
		sendMessage(channel, user.toString)
	}
	
	override protected def onIncomingChatRequest(chat:DccChat) = {
		ChatHandler.accept(chat)
	}
}