package io.arkeus.sword.activity.battle

import io.arkeus.sword.data.Element

abstract trait Fightable {
	def hp:Int = 20 + stat("stamina") * 5
	def mp:Int = 20 + stat("spirit") * 5
	
	def stat(stat:String):Int
	def damage:Int
	def armor:Int
	def element:Element.Value
}