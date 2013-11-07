package io.arkeus.sword.activity.battle

import io.arkeus.sword.data.AttackType
import io.arkeus.sword.user.item.Shield
import io.arkeus.sword.user.item.Armor

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
	
	val weapon = source.weapon
	val damage = source.damage
	val armor = source.armor
	
	def dead = hp <= 0
	
	def attack(target:BattleEntity) = {
		if (!checkForHit(this, target)) {
			new AttackInfo(this, target, AttackType.Miss, weapon.name, 0, weapon.element)
		} else {
			val dmg = calculateDamage(this, target, "weapon")
			target.hit(dmg)
			new AttackInfo(this, target, AttackType.Weapon, weapon.name, dmg, weapon.element)
		}
	}
	
	def calculateDamage(source: BattleEntity, target: BattleEntity, attackType: String) = {
		val offensive = if (attackType == "weapon") source.strength else source.wisdom
		val defensive = target.defense
		
		val difference = (offensive - defensive) / 25
		val staticBonus = offensive / 2
		val staticDefense = defensive / 2
		
		var dmg = Math.max(1, source.damage.toDouble + staticBonus - staticDefense)
		if (difference > 0) {
			dmg *= (1 + difference)
		} else {
			dmg /= (difference - 1).abs
		}
		
		val armorProtection = calculateArmorReduction(armor, (Shield.armor(target.level) + Armor.armor(target.level)) * 0.5)
		dmg *= (1 - armorProtection)
		
		dmg.ceil.toInt
	}
	
	def calculateArmorReduction(armor: Double, targetArmor: Double) = {
		var armorProtection: Double = 0
		if (armor > targetArmor) {
			armorProtection = 0.6
		} else {
			armorProtection = (armor / targetArmor) * 0.6
		}
		armorProtection
	}
	
	def checkForHit(source: BattleEntity, target: BattleEntity) = {
		val accuracy = (90 + (source.agility - target.agility) / 1.5) / 100
		Math.random() < accuracy
	}
	
	def hit(damage:Int) = {
		hp -= damage
		if (hp < 0) {
			hp = 0
		}
		damage
	}
}