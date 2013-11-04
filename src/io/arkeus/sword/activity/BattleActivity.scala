package io.arkeus.sword.activity

import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.activity.battle.Area
import io.arkeus.sword.activity.battle.Battle
import io.arkeus.sword.activity.battle.reward.GoldReward
import io.arkeus.sword.user.message.Colorfier
import io.arkeus.sword.activity.battle.reward.ExperienceReward

class BattleActivity(user: SwordUser, args: List[Any]) extends Activity(user, args) with Colorfier {
	val area: Area = args(0).asInstanceOf[Area]
	var battle: Battle = null
	var continued: Boolean = false

	override def tick = {
		state match {
			case Waiting => {
				user.send("{You start out on your adventure, use ''stop'' to stop at any time}")
				state = Exploring
			}
			case Exploring => {
				val enemy = area.randomEnemy
				battle = new Battle(user, enemy)
				user.send(s"You ${if (continued) "continue" else "begin"} exploring into the ''${area.name}'' and encounter ''${enemy.name}'' (Level ${enemy.level})!")
				state = Battling
			}
			case Battling => {
				user.send(battle.turn)
				if (battle.complete) {
					state = Rewarding
					continued = true
				}
			}
			case Rewarding => {
				if (battle.rewards.length < 1) {
					user.send("You received no rewards for the battle...")
				}
				
				for (rewards <- battle.rewards) {
					rewards match {
						case gold: GoldReward => {
							user.gainGold(gold.amount)
							user.send(s"You found <:red>''${gold.amount}''<:> gold! <:gray>(${user.gold} total)<:>")
						}
						case experience: ExperienceReward => {
							val leveledUp = user.gainExperience(experience.amount)
							user.send(s"You gained <:green>''${experience.amount}''<:> experience! <:gray>(${user.experience.current}/${user.experience.max})<:>")
							if (leveledUp) {
								user.send(s"{''Level Up''} You've reached level <:pink>''${user.level}''<:>!")
							}
						}
					}
				}
				
				battle = null
				state = Exploring
			}
			case _ => throw new IllegalStateException(s"Invalid battle state: $state")
		}
	}

	//override def initialize = user.send("Entering battle")
	override def destroy = user.send(s"You leave the ${area.name} and stop exploring")
	override def tickLength = 500

	case object Exploring extends ActivityState
	case object Battling extends ActivityState
	case object Rewarding extends ActivityState
}