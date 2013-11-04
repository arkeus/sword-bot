package io.arkeus.sword.user.item

import scala.collection.mutable.ListBuffer

class Inventory {
	val items = ListBuffer[Item]()

	def add(item: Item) = items += item
	def remove(index: Int) = items.remove(index)
	def remove(item: Item) = items.remove(items.indexOf(item))
	def get(index: Int) = {
		try {
			items(index)
		} catch {
			case _: Throwable => null
		}
	}
	def all = items

	def size = items.length
	def empty = size == 0
	
	def serialize = items.map(_.serialize).toList
	def unserialize(data: List[Map[String, Any]]) = {
		if (data != null) {
			for (itemData <- data) {
				val item = ItemDatabase.byName(itemData("name").asInstanceOf[String])
				add(item.get)
			}
		}
	}
}