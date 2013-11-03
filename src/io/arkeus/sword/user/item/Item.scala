package io.arkeus.sword.user.item

abstract class Item(val name: String, val level: Int) {
	def itemtype:String
	def subinfo:String
	
	override def toString = name
	
	def serialize = {
		Map("name" -> name)
	}
}