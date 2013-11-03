package io.arkeus.sword.activity.battle

import io.arkeus.sword.data.AttackType

class BattleEntity(source:Fightable) {
	val name = source.name
	
	val hpm = source.hp
	val mpm = source.mp
	var hp = hpm
	var mp = mpm
	
	val level = source.level
	
	val strength = source.stat("strength")
	val defense = source.stat("defense")
	val agility = source.stat("agility")
	val wisdom = source.stat("wisdom")
	val stamina = source.stat("stamina")
	val spirit = source.stat("spirit")
	
	val damage = source.damage
	val armor = source.armor
	val weapon = source.weapon
	
	def dead = hp <= 0
	
	def attack(target:BattleEntity) = {
		if (Math.random() < 0.2) {
			new AttackInfo(this, target, AttackType.Miss, weapon.name, 0, weapon.element)
		} else {
			val dmg = calculateDamage(this, target)
			target.hit(dmg)
			new AttackInfo(this, target, AttackType.Weapon, weapon.name, dmg, weapon.element)
		}
	}
	
	def calculateDamage(source: BattleEntity, target: BattleEntity) = {
		source.damage + 1
	}
	
	def hit(damage:Int) = {
		hp -= damage
		if (hp < 0) {
			hp = 0
		}
		damage
	}
}