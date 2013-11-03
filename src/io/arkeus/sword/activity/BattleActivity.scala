package io.arkeus.sword.activity

import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.activity.battle.Area
import io.arkeus.sword.activity.battle.Battle

class BattleActivity(user: SwordUser, args: List[Any]) extends Activity(user, args) {
	val area: Area = args(0).asInstanceOf[Area]
	var battle: Battle = null

	override def tick = {
		state match {
			case Waiting => {
				user.send("You start out on your adventure, use ''stop'' to stop at any time")
				state = Exploring
			}
			case Exploring => {
				val enemy = area.randomEnemy
				battle = new Battle(user, enemy)
				user.send("I GOTS ME AN ENEMY " + enemy)
				state = Battling
			}
			case Battling => {
				user.send(battle.turn)
				if (battle.complete) {
					state = Finishing
				}
			}
			case Finishing => {
				user.send("finishing")
				battle = null
				state = Exploring
			}
			case _ => throw new IllegalStateException(s"Invalid battle state: $state")
		}
	}

	override def initialize = user.send("Entering battle")
	override def destroy = user.send("Leaving battle")
	override def tickLength = 500

	case object Exploring extends ActivityState
	case object Battling extends ActivityState
	case object Finishing extends ActivityState
}