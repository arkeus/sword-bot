package io.arkeus.sword.user.item

import io.arkeus.sword.data.AffixType
import io.arkeus.sword.data.Rarity

class Affix(val name: String, val category: AffixType.Value, val rarity: Rarity.Value, val properties: Map[String, Double], val base: Int = 0) {
	
}