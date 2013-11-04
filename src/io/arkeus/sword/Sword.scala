package io.arkeus.sword

import org.jibble.pircbot.PircBot
import org.apache.logging.log4j.LogManager
import io.arkeus.sword.config.Config
import org.jibble.pircbot.NickAlreadyInUseException
import io.arkeus.sword.user.Users
import org.jibble.pircbot.DccChat
import io.arkeus.sword.util.Logger
import io.arkeus.sword.activity.battle.AreaDatabase
import io.arkeus.sword.activity.battle.MonsterDatabase

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
		MonsterDatabase.all
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
		sendMessage(channel, s"sender: $sender, login: $login, hostname: $hostname")
	}

	override protected def onIncomingChatRequest(chat: DccChat) = {
		sendMessage(config.channel, s"[DDC] nick: ${chat.getNick()}, login: ${chat.getLogin()}, hostname: ${chat.getHostname()}, address: ${chat.getNumericalAddress()}")
		// Hard coding this to prevent abuse during testing
		if (chat.getNick().toLowerCase() == "arkeus") {
			val user = Users.find(chat.getNick())
			user.administrator = config.administrators.isAdministrator(chat.getLogin(), chat.getHostname())
			user.open(chat)
		}
	}
}