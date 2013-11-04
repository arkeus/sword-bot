package io.arkeus.sword.user.item

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers._
import org.junit.Test
import io.arkeus.sword.chat.command.router.parse._
import io.arkeus.sword.data.Element
import org.jibble.pircbot.Colors
import io.arkeus.sword.user.message.Colorizer

class TooltipBuilderTest {
	@Test
	def minWidthTest = {
		val item = new Weapon("Simple", 1, Element.Physical)
		val tooltip = Colors.removeFormattingAndColors(Colorizer.colorize(new TooltipBuilder(item).build))
		assertThat(tooltip, is("[       Simple       ]\n[ Weapon    3 Damage ]\n[ Level 1    0 Slots ]"))
	}
}