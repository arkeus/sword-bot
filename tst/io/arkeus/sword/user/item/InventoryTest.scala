package io.arkeus.sword.user.item

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers._
import org.junit.Test
import scala.collection.mutable.ListBuffer

class InventoryTest {
	@Test
	def emptyInventoryTest = {
		val inventory = new Inventory
		assertThat(inventory.size, is(0))
		assertThat(inventory.empty, is(true))
		assertThat(inventory.all, is(new ListBuffer[InventoryItem]))
		assertThat(inventory.get(0), is(nullValue))
	}
	
	@Test
	def modifyInventoryTest = {
		val inventory = new Inventory
		val sword = new Weapon("Sword", 5).toInventory
		val shield = new Shield("Shield", 4).toInventory
		
		inventory.add(sword)
		assertThat(inventory.size, is(1))
		assertThat(inventory.empty, is(false))
		assertThat(inventory.get(0), is(sword))
		
		inventory.add(sword)
		assertThat(inventory.size, is(2))
		assertThat(inventory.empty, is(false))
		assertThat(inventory.get(0), is(sword))
		assertThat(inventory.get(1), is(sword))
		
		inventory.add(shield)
		assertThat(inventory.size, is(3))
		assertThat(inventory.empty, is(false))
		assertThat(inventory.get(0), is(sword))
		assertThat(inventory.get(1), is(sword))
		assertThat(inventory.get(2), is(shield))
		
		inventory.remove(0)
		assertThat(inventory.size, is(2))
		assertThat(inventory.empty, is(false))
		assertThat(inventory.get(0), is(sword))
		assertThat(inventory.get(1), is(shield))
		
		inventory.remove(sword)
		assertThat(inventory.size, is(1))
		assertThat(inventory.empty, is(false))
		assertThat(inventory.get(0), is(shield))
		
		inventory.remove(0)
		assertThat(inventory.size, is(0))
		assertThat(inventory.empty, is(true))
		assertThat(inventory.all, is(new ListBuffer[InventoryItem]))
		assertThat(inventory.get(0), is(nullValue))
		
	}
}