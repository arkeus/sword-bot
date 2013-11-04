package io.arkeus.sword.user.item

import io.arkeus.sword.util.RandomUtils

object ItemFinder {
	def find(level: Int) = {
		val minLevel = Math.max(1, Math.min((level / 2.0).ceil.toInt, level - 10))
		val maxLevel = level + 1
		val items = ItemDatabase.all.filter(item => item.level >= minLevel && item.level <= maxLevel)
		if (items.length > 0) {
			Some(items(RandomUtils.random(0, items.length - 1)))
		} else {
			None
		}
	}
}