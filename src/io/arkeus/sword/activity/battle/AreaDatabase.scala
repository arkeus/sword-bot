package io.arkeus.sword.activity.battle

import io.arkeus.sword.util.Database

object AreaDatabase extends Database[Area] {
	def build(list: List[(String, List[String])]) = {
		var id = 1
		for (info <- list) {
			val name = info._1
			val monsters = info._2.map(MonsterDatabase.byName(_).get)
			val area = new Area(id, name, monsters)
			ids += area
			names.put(name, area)
			id += 1
		}
	}
	
	build(List(
		("Fields", List("Baby Earth Slime", "Small River Rat", "Baby Fire Slime", "Young Hawk"))
	))
}