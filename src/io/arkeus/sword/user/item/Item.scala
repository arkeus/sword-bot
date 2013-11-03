package io.arkeus.sword.user.item

abstract class Item(val name: String, val level: Int) {
	def itemtype:String
	def subinfo:String
}