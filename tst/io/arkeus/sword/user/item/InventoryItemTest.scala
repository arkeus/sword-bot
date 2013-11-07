package io.arkeus.sword.user.item

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers._
import org.junit.Test
import scala.collection.mutable.ListBuffer
import org.jibble.pircbot.Colors
import io.arkeus.sword.user.message.Colorizer

class InventoryItemTest {
	@Test
	def testColoredName = {
		val prefix = AffixDatabase.byName("Potent").get
		val suffix = AffixDatabase.byName("of Strength").get
		val item = ItemDatabase.byName("Dwarven Short Sword").get.toInventory.withPrefix(prefix).withSuffix(suffix)
		assertThat(Colorizer.colorize(item.toString), is(Colors.BLUE + "Potent Dwarven Short Sword of Strength" + Colors.NORMAL))
	}
	
	@Test
	def testKeys = {
		val item = ItemDatabase.byName("Dwarven Short Sword").get.toInventory
		assertThat(item.keys, is(Set[String]()))
		assertThat(item.stat("Strength"), is(0))
		
		val prefix = AffixDatabase.byName("Potent").get
		val suffix = AffixDatabase.byName("of Defense").get
		val itemMods = ItemDatabase.byName("Dwarven Short Dagger").get.toInventory.withPrefix(prefix).withSuffix(suffix)
		assertThat(itemMods.keys, is(Set[String]("Strength", "Defense")))
		assertThat(itemMods.stat("Strength"), is(2))
		assertThat(itemMods.stat("Defense"), is(2))
		assertThat(itemMods.stat("Wisdom"), is(0))
	}
}