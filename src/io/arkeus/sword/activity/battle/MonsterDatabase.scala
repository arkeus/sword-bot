package io.arkeus.sword.activity.battle

import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import io.arkeus.sword.util.Database

object MonsterDatabase extends Database[Monster] {	
	def build(list: List[(String, Int)]) = {
		var id = 1
		for (info <- list) {
			val name = info._1
			val level = info._2
			val monster = new Monster(id, name, level)
			ids += monster
			names.put(name, monster)
			id += 1
		}
	}
	
	build(List(
		// Fields
		("Baby Earth Slime", 1),
		("Small River Rat", 1),
		("Baby Fire Slime", 1),
		("Young Hawk", 2)
	))
}