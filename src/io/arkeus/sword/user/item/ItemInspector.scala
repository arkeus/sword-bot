package io.arkeus.sword.user.item

import org.jibble.pircbot.Colors

object ItemInspector {
	def inspect(item: Item) = {
		List(
			s"{''${item.name}'' - ${Colors.LIGHT_GRAY}0 Slots${Colors.NORMAL}}",
			s"${item.itemtype} (${item.subinfo})"
		)
	}
}