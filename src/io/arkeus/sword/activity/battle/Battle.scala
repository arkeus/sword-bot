package io.arkeus.sword.activity.battle

class Battle(val sourceFightable: Fightable, val targetFightable: Fightable) {
	val source = new BattleEntity(sourceFightable)
	val target = new BattleEntity(targetFightable)
	var current = if (source.agility > target.agility) source else target
	var complete = false
	
	def turn = {
		if (target.dead) {
			complete = true
			"you win"
		} else if (source.dead) {
			complete = true
			"you lose"
		} else {
			var self = current
			var other = if (current == source) target else source
			self.attack(other)
			"attack happened"
		}
	}
}