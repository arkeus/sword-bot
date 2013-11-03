package io.arkeus.sword.activity.battle

import io.arkeus.sword.data.AttackType
import io.arkeus.sword.data.Element
import io.arkeus.sword.user.message.Colorfier

class AttackInfo(val from: BattleEntity, val to: BattleEntity, val attackType: AttackType.Value, val attackName: String, val damage: Int, val element: Element.Value) extends Colorfier {
	def format(you:BattleEntity) = {
		val sb = new StringBuilder
		
		if (you == from) {
			if (attackType == AttackType.Miss) {
				sb.append(s"You swing your ''${ce(attackName, element)}'' at the ''${to.name}'' but you miss!")
			} else if (attackType == AttackType.Weapon) {
				sb.append(s"You swing your ''${ce(attackName, element)}'' at the ''${to.name}'' for ''${damage}'' ''${element}'' damage!")
			} else {
				
			}
		} else {
			if (attackType == AttackType.Miss) {
				sb.append(s"The ''${from.name}'' attacks you with its ''${attackName}'', but it misses!")
			} else if (attackType == AttackType.Weapon) {
				sb.append(s"The ''${from.name}'' attacks you with its ''${attackName}'' for ''${damage}'' ''${element}'' damage!")
			} else {
				
			}
		}
		
		sb.append(s" <:gray>(${to.hp} hp remaining)<:>")
		sb.toString
	}
}