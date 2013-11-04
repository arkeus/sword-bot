package io.arkeus.sword.activity.battle

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers._
import org.junit.Test
import io.arkeus.sword.user.SwordUser

class BattleEntityTest {
	@Test
	def armorFormulaTest = {
		val user = new SwordUser("test")
		val entity = new BattleEntity(user)
		
		assertThat(entity.calculateArmorReduction(10, 20).asInstanceOf[java.lang.Double], closeTo(0.3, 0.01))
		assertThat(entity.calculateArmorReduction(10, 21).asInstanceOf[java.lang.Double], closeTo(0.285, 0.01))
		assertThat(entity.calculateArmorReduction(10, 200).asInstanceOf[java.lang.Double], closeTo(0.03, 0.01))
		assertThat(entity.calculateArmorReduction(10, 10).asInstanceOf[java.lang.Double], closeTo(0.6, 0.01))
		assertThat(entity.calculateArmorReduction(10, 9).asInstanceOf[java.lang.Double], closeTo(0.6, 0.01))
		assertThat(entity.calculateArmorReduction(75, 100).asInstanceOf[java.lang.Double], closeTo(0.45, 0.01))
	}
}