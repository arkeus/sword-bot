package io.arkeus.sword.activity

import scala.actors.Actor
import io.arkeus.sword.util.Logger
import scala.actors.TIMEOUT
import io.arkeus.sword.user.SwordUser

abstract class Activity(val user: SwordUser, val args: List[Any]) extends Actor with Logger {
	var state: ActivityState = Waiting

	override def act = {
		initialize
		loop {
			reactWithin(tickLength) {
				case Stop => destroy; close
				case TIMEOUT => tick
			}
		}
	}

	def tickLength = 500

	// Called every $tickLength time, override with activity behavior
	def tick(): Unit

	// Override to get initialization logic
	def initialize(): Unit = {}

	// Override to get destructor logic
	def destroy(): Unit = {}
	
	def close = {
		user.finishActivity
		exit
	}

	class ActivityState
	case object Waiting extends ActivityState
}

case object Stop