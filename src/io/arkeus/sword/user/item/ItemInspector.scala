package io.arkeus.sword.user.item

import org.jibble.pircbot.Colors

object ItemInspector {
	def inspect(item: InventoryItem) = new TooltipBuilder(item).build
}