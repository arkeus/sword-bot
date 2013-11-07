package io.arkeus.sword.user.item

import io.arkeus.sword.data.AffixType
import io.arkeus.sword.data.Rarity

class Affix(val name: String, val category: AffixType.Value, val rarity: Rarity.Value, val properties: Map[String, (Double, Int)]) {
	val keys: Set[String] = properties.keySet
}

object Affix {
	def stat(name: String, level: Int, affix: Affix) = {
		if (affix == null) {
			0
		} else {
			val (value, base) = affix.properties.getOrElse(name, (0.0, 0))
			level * value + base
		}
	}
}