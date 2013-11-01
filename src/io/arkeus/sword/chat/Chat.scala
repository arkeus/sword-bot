package io.arkeus.sword.chat

import org.jibble.pircbot.DccChat
import scala.actors.Actor
import java.io.IOException
import scala.collection.Iterator
import io.arkeus.sword.user.Users
import org.apache.logging.log4j.LogManager
import scala.util.Random
import io.arkeus.sword.util.Logger
import io.arkeus.sword.chat.command.CommandMap

class Chat(val chat:DccChat) extends Actor with Logger {
	val user = Users.find(chat.getNick())
	
	override def act() = {
		try {
			logger.info(s"Initiating chat with ${user.name}")
			chat.accept()
			user.chat = chat
			
			Iterator.continually(chat.readLine).takeWhile(_ != null).foreach(processLine(_))
		} catch {
			case _:IOException => println("OH NO IO EXCEPTION CALL THE KHALEESI")
		} finally {
			logger.info(s"Completing chat with ${user.name}")
			user.chat = null
			chat.close()
		}
	}
	
	private def processLine(line:String) = {
		try {
			CommandMap.execute(user, new Message(line))
		} catch {
			case exception:Throwable => logger.error(s"Error with line '$line' from user '$user'", exception)
		}
	}
}