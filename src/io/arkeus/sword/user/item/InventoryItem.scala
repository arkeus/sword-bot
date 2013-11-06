package io.arkeus.sword.user.item

import io.arkeus.sword.data.Rarity
import io.arkeus.sword.data.Element

class InventoryItem(val item: Item) {
	var prefix: Affix = null
	var suffix: Affix = null

	def name = s"${if (prefix != null) prefix.name + " " else ""}${item.name}${if (suffix != null) " " + suffix.name else ""}"

	def damage = item match {
		case weapon: Weapon => weapon.damage
		case _ => 0
	}

	def armor = item match {
		case armor: Armor => armor.armor
		case shield: Shield => shield.armor
		case _ => 0
	}

	def element = item match {
		case weapon: Weapon => weapon.element
		case _ => Element.Physical
	}

	def rarity = {
		val prefixPoints = if (prefix == null) { 0 } else if (prefix.rarity == Rarity.Common) 1 else 2
		val suffixPoints = if (suffix == null) { 0 } else if (suffix.rarity == Rarity.Common) 1 else 2
		(prefixPoints + suffixPoints) match {
			case 0 => Rarity.Common
			case 1 => Rarity.Uncommon
			case 2 => Rarity.Rare
			case 3 => Rarity.Mythic
			case _ => Rarity.Legendary
		}
	}

	def withPrefix(affix: Affix) = {
		prefix = affix
		this
	}

	def withSuffix(affix: Affix) = {
		suffix = affix
		this
	}

	def coloredName = s"<:${Rarity.toColor(rarity)}>$name<:>"
	override def toString = coloredName

	def serialize = {
		Map(
			"name" -> item.name,
			"prefix" -> (if (prefix == null) null else prefix.name),
			"suffix" -> (if (suffix == null) null else suffix.name))
	}
}