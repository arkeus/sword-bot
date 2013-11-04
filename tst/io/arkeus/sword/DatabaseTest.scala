package io.arkeus.sword

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers._
import org.junit.Test
import com.twitter.json.Json
import io.arkeus.sword.activity.battle.AreaDatabase
import io.arkeus.sword.user.item.ItemDatabase

class DatabaseTest {
	@Test
	def testDatabases:Unit = {
		println(ItemDatabase.all.map(item => s"${item.name} ${item.level}").mkString("\n"))
		ItemDatabase.all
		AreaDatabase.all
	}
}