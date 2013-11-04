package io.arkeus.sword.activity.battle

import io.arkeus.sword.data.Element
import io.arkeus.sword.data.AttackType
import scala.collection.mutable.ListBuffer
import io.arkeus.sword.activity.battle.reward.Reward
import io.arkeus.sword.activity.battle.reward.ExperienceReward
import io.arkeus.sword.activity.battle.reward.GoldReward
import io.arkeus.sword.util.RandomUtils
import io.arkeus.sword.user.item.ItemFinder
import io.arkeus.sword.activity.battle.reward.ItemReward

class Battle(val sourceFightable: Fightable, val targetFightable: Fightable) {
	val source = new BattleEntity(sourceFightable)
	val target = new BattleEntity(targetFightable)
	var current = if (source.agility > target.agility) source else target
	var complete = false
	var rewards = ListBuffer[Reward]()
	
	def turn = {
		if (target.dead) {
			complete = true
			buildRewards
			s"You've defeated the ${target.name}!"
		} else if (source.dead) {
			complete = true
			s"The ${target.name} has defeated you..."
		} else {
			var self = current
			var other = if (current == source) target else source
			val attack = self.attack(other)
			val message = attack.format(source)
			
			current = if (current == source) target else source
			
			message
		}
	}
	
	private def buildRewards = {
		val baseExperience = (3 + math.pow(target.level, 1.3))
		val experience = RandomUtils.random((baseExperience * 0.8 - 1).ceil.toInt, (baseExperience * 1.2 + 1).ceil.toInt)
		val gold = (2 + target.level * 1.3).ceil.toInt
		
		rewards += new ExperienceReward(experience)
		rewards += new GoldReward(gold)
		
		if (Math.random() < 0.5) {
			ItemFinder.find(target.level) match {
				case Some(item) => rewards += new ItemReward(item)
				case None =>
			}
		}
	}
}