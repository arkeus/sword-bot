package io.arkeus.sword

import org.jibble.pircbot.PircBot
import org.apache.logging.log4j.LogManager
import io.arkeus.sword.config.Config
import org.jibble.pircbot.NickAlreadyInUseException
import io.arkeus.sword.user.Users
import org.jibble.pircbot.DccChat
import io.arkeus.sword.util.Logger
import io.arkeus.sword.activity.battle.AreaDatabase

class Sword(val config: Config) extends PircBot with Logger {
	def initialize = {
		logger.info("Priming databases")
		prime
		
		logger.info("Initializing data")
		SwordData.initialize(config)
		
		logger.info(s"Connecting to ${config.server}")

		setLogin(config.login)
		setVerbose(config.verbose)
		setName(config.nick)
		setVersion(config.login)

		connect(config.server)
	}
	
	def prime = {
		AreaDatabase.all
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

	override protected def onMessage(channel: String, sender: String, login: String, hostname: String, message: String) = {
		// nothing yo
	}
	
	override protected def onPrivateMessage(sender: String, login: String, hostname: String, message: String) = {
		sendMessage(sender, "To interact with Sword Bot, you must open a DCC chat. If you client doesn't support it, get a better client.")
	}

	override protected def onIncomingChatRequest(chat: DccChat) = {
		val user = Users.find(chat.getNick())
		if (!Users.isActive(user.name)) {
			user.administrator = config.administrators.isAdministrator(chat.getLogin(), chat.getHostname())
			user.open(chat)
		} else {
			chat.close()
		}
	}
}