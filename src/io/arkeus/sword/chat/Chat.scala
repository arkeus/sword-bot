package io.arkeus.sword.chat

import java.io.IOException
import org.jibble.pircbot.DccChat
import scala.actors.Actor
import scala.collection.Iterator
import io.arkeus.sword.chat.command.CommandRouter
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.util.Logger

class Chat(val chat: DccChat, val user: SwordUser) extends Actor with Logger {
	def accept = chat.accept
	def close = chat.close
	def send(message:String) = chat.sendLine(message)
	
	override def act() = {
		try {
			Iterator.continually(chat.readLine).takeWhile(_ != null).foreach(processLine(_))
		} catch {
			case _: IOException => println("OH NO IO EXCEPTION CALL THE KHALEESI")
		} finally {
			logger.info(s"Completing chat with ${user.name}")
			user.close
		}
	}

	private def processLine(line: String) = {
		try {
			user.save
			CommandRouter.execute(user, line)
		} catch {
			case exception: Throwable => logger.error(s"Error with line '$line' from user '$user'", exception)
		}
	}
}