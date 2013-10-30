package io.arkeus.sword.user.item

class Armor(name:String, level:Int) extends Item(name, level) {
	val armor = level * 2
}