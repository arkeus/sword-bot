package io.arkeus.sword.activity.battle

class BattleEntity(source:Fightable) {
	val hpm = source.hp
	val mpm = source.mp
	var hp = hpm
	var mp = mpm
	
	val strength = source.stat("strength")
	val defense = source.stat("defense")
	val agility = source.stat("agility")
	val wisdom = source.stat("wisdom")
	val stamina = source.stat("stamina")
	val spirit = source.stat("spirit")
	
	val damage = source.damage
	val armor = source.armor
	val element = source.element
	
	def dead = hp <= 0
	
	def attack(target:BattleEntity) = {
		target.hit(damage)
	}
	
	def hit(damage:Int) = {
		hp -= damage
		if (hp < 0) {
			hp = 0
		}
	}
}