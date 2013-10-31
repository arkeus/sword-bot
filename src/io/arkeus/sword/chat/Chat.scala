package io.arkeus.sword.chat

import org.jibble.pircbot.DccChat
import scala.actors.Actor
import java.io.IOException
import scala.collection.Iterator
import io.arkeus.sword.user.Users
import org.apache.logging.log4j.LogManager
import scala.util.Random

class Chat(val chat:DccChat) extends Actor {
	val logger = LogManager.getLogger(this.getClass)
	val user = Users.find(chat.getNick())
	
	override def act() = {
		try {
			logger.info(s"Initiating chat with ${user.name}")
			chat.accept()
			
			Iterator.continually(chat.readLine).takeWhile(_ != null).foreach(processLine(_))
			
			logger.info(s"Completing chat with ${user.name}")
			chat.close()
		} catch {
			case _:IOException => println("OH NO IO EXCEPTION CALL THE KHALEESI")
		}
	}
	
	private def processLine(line:String) = {
		chat.sendLine(s"Hello $user, you just sent me '$line', have some experience!")
		user.experience.gain(Random.nextInt(50) + 50)
	}
}