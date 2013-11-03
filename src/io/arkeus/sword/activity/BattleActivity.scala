package io.arkeus.sword.activity

import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.activity.battle.Area

class BattleActivity(user:SwordUser, args:List[Any]) extends Activity(user, args) {
	val area: Area = args(0).asInstanceOf[Area]
	
	override def tick = {
		state match {
			case Waiting => {
				user.send("Battling, ''stop'' to stop")
			}
			case Exploring => {
				
			}
			case Battling => {
				
			}
			case Finishing => {
				
			}
			case _ => throw new IllegalStateException(s"Invalid battle state: $state")
		}
	}
	
	override def initialize = user.send("Entering battle")
	override def destroy = user.send("Leaving battle")
	override def tickLength = 2000
	
	case object Exploring extends ActivityState
	case object Battling extends ActivityState
	case object Finishing extends ActivityState
}