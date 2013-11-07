package io.arkeus.sword.user.item

abstract class Item(val name: String, val level: Int, val properties: Map[String, Double]) {
	def itemtype: String
	def subinfo: String
	def shortinfo: String

	override def toString = name
	def toInventory = new InventoryItem(this)
	def keys = if (properties == null) Set[String]() else properties.keySet
	def stat(name: String) = properties.getOrElse(name, 0.0)
}