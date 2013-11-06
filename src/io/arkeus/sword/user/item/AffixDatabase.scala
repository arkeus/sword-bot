package io.arkeus.sword.user.item

import io.arkeus.sword.util.Database
import io.arkeus.sword.data.AffixType
import io.arkeus.sword.data.Rarity

object AffixDatabase extends Database[Affix] {
	def build(affixes: List[(String, AffixType.Value, Rarity.Value, Map[String, Double], Int)]) = {
		var id = 1
		for (affix <- affixes) {
			val name = affix._1
			val category = affix._2
			val rarity = affix._3
			val properties = affix._4
			val base = affix._5
			val aff = new Affix(name, category, rarity, properties, base)
			ids += aff
			names.put(name, aff)
			id += 1
		}
	}

	def find(rarity: Rarity.Value, category: AffixType.Value) = {
		if (rarity == Rarity.Common) {
			if (category == AffixType.Prefix) commonPrefixes else commonSuffixes
		} else {
			if (category == AffixType.Prefix) rarePrefixes else rareSuffixes
		}
	}

	build(List(
		////// Prefix
		//// Common
		// BASE STATS
		("Potent", AffixType.Prefix, Rarity.Common, Map("Strength" -> 0.6), 1),
		("Fortified", AffixType.Prefix, Rarity.Common, Map("Defense" -> 0.6), 1),
		("Mystic", AffixType.Prefix, Rarity.Common, Map("Wisdom" -> 0.6), 1),
		("Hare's", AffixType.Prefix, Rarity.Common, Map("Agility" -> 0.1), 0),
		("Vitalized", AffixType.Prefix, Rarity.Common, Map("Stamina" -> 0.6), 1),
		("Spiritual", AffixType.Prefix, Rarity.Common, Map("Spirit" -> 0.6), 1),
		// ALT STATS
		("Greedy", AffixType.Prefix, Rarity.Common, Map("Greed" -> 0.1), 0),
		("Lucky", AffixType.Prefix, Rarity.Common, Map("Luck" -> 0.1), 0),
		("Targeted", AffixType.Prefix, Rarity.Common, Map("Crit" -> 0.1), 0),
		("Speedy", AffixType.Prefix, Rarity.Common, Map("Haste" -> 0.1), 0),
		// RESISTS
		("Firewall", AffixType.Prefix, Rarity.Common, Map("Fire Resistance" -> 0.3), 10),
		("Icewall", AffixType.Prefix, Rarity.Common, Map("Ice Resistance" -> 0.3), 10),
		("Earthwall", AffixType.Prefix, Rarity.Common, Map("Earth Resistance" -> 0.3), 10),
		("Airwall", AffixType.Prefix, Rarity.Common, Map("Air Resistance" -> 0.3), 10),
		("Bladewall", AffixType.Prefix, Rarity.Common, Map("Physical Resistance" -> 0.3), 10),
		//// Rare
		// BASE STATS
		("Rigorous", AffixType.Prefix, Rarity.Rare, Map("Strength" -> 0.8), 2),
		("Shelled", AffixType.Prefix, Rarity.Rare, Map("Defense" -> 0.8), 2),
		("Spectral", AffixType.Prefix, Rarity.Rare, Map("Wisdom" -> 0.8), 2),
		("Fox's", AffixType.Prefix, Rarity.Rare, Map("Agility" -> 0.15), 0),
		("Shielded", AffixType.Prefix, Rarity.Rare, Map("Stamina" -> 0.8), 2),
		("Seraphic", AffixType.Prefix, Rarity.Rare, Map("Spirit" -> 0.8), 2),
		// ALT STATS
		("Golden", AffixType.Prefix, Rarity.Rare, Map("Greed" -> 0.15), 0),
		("Guiding", AffixType.Prefix, Rarity.Rare, Map("Luck" -> 0.15), 0),
		("Focused", AffixType.Prefix, Rarity.Rare, Map("Crit" -> 0.15), 0),
		("Mirrored", AffixType.Prefix, Rarity.Rare, Map("Haste" -> 0.15), 0),
		// RESIST
		("Blamewall", AffixType.Prefix, Rarity.Rare, Map("Fire Resistance" -> 0.5), 15),
		("Frostwall", AffixType.Prefix, Rarity.Rare, Map("Ice Resistance" -> 0.5), 15),
		("Stonewall", AffixType.Prefix, Rarity.Rare, Map("Earth Resistance" -> 0.5), 15),
		("Windwall", AffixType.Prefix, Rarity.Rare, Map("Air Resistance" -> 0.5), 15),
		("Swordwall", AffixType.Prefix, Rarity.Rare, Map("Physical Resistance" -> 0.5), 15),
		// DUAL RESIST
		("Volcanoward", AffixType.Prefix, Rarity.Rare, Map("Fire Resistance" -> 0.4, "Earth Resistance" -> 0.4), 10),
		("Blizzardward", AffixType.Prefix, Rarity.Rare, Map("Ice Resistance" -> 0.4, "Air Resistance" -> 0.4), 10),
		("Steamward", AffixType.Prefix, Rarity.Rare, Map("Fire Resistance" -> 0.4, "Ice Resistance" -> 0.4), 10),
		("Spireward", AffixType.Prefix, Rarity.Rare, Map("Air Resistance" -> 0.4, "Earth Resistance" -> 0.4), 10),
		////// Suffix
		//// Common
		// BASE STATS
		("of Strength", AffixType.Suffix, Rarity.Common, Map("Strength" -> 0.6), 1),
		("of Defense", AffixType.Suffix, Rarity.Common, Map("Defense" -> 0.6), 1),
		("of Wisdom", AffixType.Suffix, Rarity.Common, Map("Wisdom" -> 0.6), 1),
		("of Agility", AffixType.Suffix, Rarity.Common, Map("Agility" -> 0.1), 0),
		("of Stamina", AffixType.Suffix, Rarity.Common, Map("Stamina" -> 0.6), 1),
		("of Spirit", AffixType.Suffix, Rarity.Common, Map("Spirit" -> 0.6), 1),
		// ALT STATS
		("of Greed", AffixType.Suffix, Rarity.Common, Map("Greed" -> 0.1), 0),
		("of Luck", AffixType.Suffix, Rarity.Common, Map("Luck" -> 0.1), 0),
		("of Aim", AffixType.Suffix, Rarity.Common, Map("Crit" -> 0.1), 0),
		("of Haste", AffixType.Suffix, Rarity.Common, Map("Haste" -> 0.1), 0),
		// RESISTS
		("of Refraction", AffixType.Suffix, Rarity.Common, Map("Fire Resistance" -> 0.1, "Earth Resistance" -> 0.1, "Ice Resistance" -> 0.1, "Air Resistance" -> 0.1), 3),
		//// Rare
		// BASE STATS
		("of War", AffixType.Suffix, Rarity.Rare, Map("Strength" -> 0.8), 2),
		("of Blocking", AffixType.Suffix, Rarity.Rare, Map("Defense" -> 0.8), 2),
		("of Wizardry", AffixType.Suffix, Rarity.Rare, Map("Wisdom" -> 0.8), 2),
		("of Sound", AffixType.Suffix, Rarity.Rare, Map("Agility" -> 0.15), 0),
		("of the Crab", AffixType.Suffix, Rarity.Rare, Map("Stamina" -> 0.8), 2),
		("of the Witch", AffixType.Suffix, Rarity.Rare, Map("Spirit" -> 0.8), 2),
		// ALT STATS
		("of Silver", AffixType.Suffix, Rarity.Rare, Map("Greed" -> 0.15), 0),
		("of the Compass", AffixType.Suffix, Rarity.Rare, Map("Luck" -> 0.15), 0),
		("of Truth", AffixType.Suffix, Rarity.Rare, Map("Crit" -> 0.15), 0),
		("of Light", AffixType.Suffix, Rarity.Rare, Map("Haste" -> 0.15), 0),
		// RESISTS
		("of Reflection", AffixType.Suffix, Rarity.Rare, Map("Fire Resistance" -> 0.2, "Earth Resistance" -> 0.2, "Ice Resistance" -> 0.2, "Air Resistance" -> 0.2), 7)))

	val commonPrefixes = all.filter(affix => affix.rarity == Rarity.Common && affix.category == AffixType.Prefix)
	val commonSuffixes = all.filter(affix => affix.rarity == Rarity.Common && affix.category == AffixType.Suffix)
	val rarePrefixes = all.filter(affix => affix.rarity == Rarity.Rare && affix.category == AffixType.Prefix)
	val rareSuffixes = all.filter(affix => affix.rarity == Rarity.Rare && affix.category == AffixType.Suffix)
}