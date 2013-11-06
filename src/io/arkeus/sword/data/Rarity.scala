package io.arkeus.sword.data

import org.jibble.pircbot.Colors

object Rarity extends Enumeration {
	type Rarity = Value

	val Common = Value("Common")
	val Uncommon = Value("Uncommon")
	val Rare = Value("Rare")
	val Mythic = Value("Mythic")
	val Legendary = Value("Legendary")
	
	def toColor(rarity: Rarity) = rarity match {
		case Common => "black"
		case Uncommon => "green"
		case Rare => "blue"
		case Mythic => "magenta"
		case Legendary => "red"
	}
}