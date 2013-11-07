package io.arkeus.sword.user.item

import io.arkeus.sword.util.RandomUtils
import io.arkeus.sword.data.AffixType
import io.arkeus.sword.data.Rarity

object ItemFinder {
	val AFFIX_CHANCE = 0.7
	val COMMON_AFFIX_CHANCE = 0.9

	def find(level: Int) = {
		val baseItem: Item = findBaseItem(level)
		val prefix: Affix = findAffix(AffixType.Prefix)
		val suffix: Affix = findAffix(AffixType.Suffix)

		if (baseItem != null) {
			Some(baseItem.toInventory.withPrefix(prefix).withSuffix(suffix))
		} else {
			None
		}
	}

	private def findAffix(category: AffixType.Value) = {
		if (Math.random() > AFFIX_CHANCE) {
			null
		} else {
			val rarity = if (Math.random() < COMMON_AFFIX_CHANCE) Rarity.Common else Rarity.Rare
			val affixes = AffixDatabase.find(rarity, category)
			if (affixes.length > 0) {
				affixes(RandomUtils.random(0, affixes.length - 1))
			} else {
				null
			}
		}
	}

	private def findBaseItem(level: Int) = {
		val minLevel = Math.max(1, Math.min((level / 2.0).ceil.toInt, level - 10))
		val maxLevel = level + 1
		val items = ItemDatabase.all.filter(item => item.level >= minLevel && item.level <= maxLevel)
		if (items.length > 0) {
			items(RandomUtils.random(0, items.length - 1))
		} else {
			null
		}
	}
}