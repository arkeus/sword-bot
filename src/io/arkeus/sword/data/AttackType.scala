package io.arkeus.sword.data

object AttackType extends Enumeration {
	type AttackType = Value

	val Weapon = Value("Weapon")
	val Spell = Value("Spell")
	val Miss = Value("Miss")
}