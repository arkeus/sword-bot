package io.arkeus.sword.activity.battle

import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import io.arkeus.sword.util.Database
import io.arkeus.sword.data.Element
import io.arkeus.sword.user.item.Weapon
import io.arkeus.sword.user.item.ItemDatabase
import io.arkeus.sword.user.item.Item

object MonsterDatabase extends Database[Monster] {	
	def build(list: List[(String, Int, Option[Item])]) = {
		var id = 1
		for (info <- list) {
			val name = info._1
			val level = info._2
			val weapon = info._3.get.asInstanceOf[Weapon]
			val monster = new Monster(id, name, level, weapon)
			ids += monster
			names.put(name, monster)
			id += 1
		}
	}
	
	build(List(
		// Fields
		("Baby Earth Slime", 1, ItemDatabase.byName("Claws")),
		("Small River Rat", 1, ItemDatabase.byName("Claws")),
		("Baby Fire Slime", 1, ItemDatabase.byName("Claws")),
		("Young Hawk", 2, ItemDatabase.byName("Claws"))
	))
}