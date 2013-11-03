package io.arkeus.sword.user

import scala.collection.mutable.HashMap

class Statistics {
	val stats = HashMap() ++ Statistic.names.map(name => (name, 10)).toMap
	
	def get(stat:String) = stats.get(stat).orElse(Some(0)).get
	def set(stat:String, value:Int) = stats.put(stat, value)
	
	override def toString = Statistic.names.map(name => s"''${Statistic.shorten(name)}'' ${stats(name)}").mkString(" | ")
}

object Statistic {
	val names = List("strength", "defense", "wisdom", "agility", "stamina", "spirit")
	
	def shorten(name:String) = name.substring(0, 3).toUpperCase()
}