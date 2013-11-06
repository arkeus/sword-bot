package io.arkeus.sword.user.item

import scala.collection.mutable.ListBuffer

class Inventory {
	val items = ListBuffer[InventoryItem]()

	def reset = items.clear
	def add(item: InventoryItem) = items += item
	def remove(index: Int) = items.remove(index)
	def remove(item: InventoryItem) = items.remove(items.indexOf(item))
	def get(index: Int) = {
		try {
			items(index)
		} catch {
			case _: Throwable => null
		}
	}
	def all = items
	def category(category: String) = all.filter(_.item.itemtype == category)

	def size = items.length
	def empty = size == 0

	def serialize = items.map(_.serialize).toList
	def unserialize(data: List[Map[String, Any]]) = {
		if (data != null) {
			for (itemData <- data) {
				val item = ItemDatabase.byName(itemData("name").asInstanceOf[String]).get.toInventory.withPrefix(AffixDatabase.byName(itemData("prefix").asInstanceOf[String]).getOrElse(null)).withSuffix(AffixDatabase.byName(itemData("suffix").asInstanceOf[String]).getOrElse(null))
				add(item)
			}
		}
	}
}