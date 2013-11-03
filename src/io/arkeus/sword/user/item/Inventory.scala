package io.arkeus.sword.user.item

import scala.collection.mutable.ListBuffer

class Inventory {
	val items = new ListBuffer[Item]
	
	def add(item:Item) = items += item
	def remove(index:Int) = items.remove(index)
	def remove(item:Item) = items.remove(items.indexOf(item))
	def get(index:Int) = items(index)
	def all = items
	
	def empty = items.length == 0
}