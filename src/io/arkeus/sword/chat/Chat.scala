package io.arkeus.sword.chat

import java.io.IOException
import org.jibble.pircbot.DccChat
import scala.actors.Actor
import scala.collection.Iterator
import io.arkeus.sword.chat.command.CommandRouter
import io.arkeus.sword.user.Users
import io.arkeus.sword.util.Logger

class Chat(val chat: DccChat) extends Actor with Logger {
	val user = Users.find(chat.getNick())

	override def act() = {
		try {
			logger.info(s"Initiating chat with ${user.name}")
			chat.accept()
			user.chat = chat

			user.send("Welcome to {Sword Bot}, home of the {Sword Bot}, can I take your order?")
			Iterator.continually(chat.readLine).takeWhile(_ != null).foreach(processLine(_))
		} catch {
			case _: IOException => println("OH NO IO EXCEPTION CALL THE KHALEESI")
		} finally {
			logger.info(s"Completing chat with ${user.name}")
			user.chat = null
			chat.close()
		}
	}

	private def processLine(line: String) = {
		try {
			CommandRouter.execute(user, line)
		} catch {
			case exception: Throwable => logger.error(s"Error with line '$line' from user '$user'", exception)
		}
	}
}