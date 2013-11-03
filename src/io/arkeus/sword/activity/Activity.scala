package io.arkeus.sword.activity

import scala.actors.Actor
import io.arkeus.sword.util.Logger
import scala.actors.TIMEOUT
import io.arkeus.sword.user.SwordUser

abstract class Activity(val user:SwordUser) extends Actor with Logger {
	var state:ActivityState = Waiting
	
	override def act = {
		initialize
		loop {
			reactWithin(tickLength) {
				case Stop => destroy; exit
				case TIMEOUT => tick
			}
		}
	}
	
	def tickLength = 500
	
	// Called every $tickLength time, override with activity behavior
	def tick():Unit
	
	// Override to get initialization logic
	def initialize():Unit = {}
	
	// Override to get destructor logic
	def destroy():Unit = {}
	
	class ActivityState
	case object Waiting extends ActivityState
}

case object Stop