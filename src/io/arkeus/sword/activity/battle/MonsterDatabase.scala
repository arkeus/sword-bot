package io.arkeus.sword.activity.battle

import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import io.arkeus.sword.util.Database
import io.arkeus.sword.data.Element
import io.arkeus.sword.user.item.Weapon
import io.arkeus.sword.user.item.ItemDatabase

object MonsterDatabase extends Database[Monster] {	
	def build(list: List[(String, Int, Weapon)]) = {
		var id = 1
		for (info <- list) {
			val name = info._1
			val level = info._2
			val weapon = info._3
			val monster = new Monster(id, name, level, weapon)
			ids += monster
			names.put(name, monster)
			id += 1
		}
	}
	
	build(List(
		// Fields
		("Baby Earth Slime", 1, ItemDatabase.Claws),
		("Small River Rat", 1, ItemDatabase.Claws),
		("Baby Fire Slime", 1, ItemDatabase.Claws),
		("Young Hawk", 2, ItemDatabase.Claws)
	))
}