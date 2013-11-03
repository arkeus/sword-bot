package io.arkeus.sword.activity.battle

import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import io.arkeus.sword.util.Database
import io.arkeus.sword.data.Element

object MonsterDatabase extends Database[Monster] {	
	def build(list: List[(String, Int, Element.Value)]) = {
		var id = 1
		for (info <- list) {
			val name = info._1
			val level = info._2
			val element = info._3
			val monster = new Monster(id, name, level, element)
			ids += monster
			names.put(name, monster)
			id += 1
		}
	}
	
	build(List(
		// Fields
		("Baby Earth Slime", 1, Element.Earth),
		("Small River Rat", 1, Element.Physical),
		("Baby Fire Slime", 1, Element.Fire),
		("Young Hawk", 2, Element.Physical)
	))
}