package io.arkeus.sword.user

import scala.collection.mutable.HashMap

class Statistics {
	val stats = HashMap() ++ Statistic.names.map(name => (name, 5)).toMap

	def get(stat: String) = stats.get(stat).orElse(Some(0)).get
	def set(stat: String, value: Int) = stats.put(stat, value)

	def unserialize(data: Map[String, Int]) = if (data != null) data.foreach { case (key, value) => stats.put(key, value) }

	override def toString = Statistic.names.map(name => s"''${Statistic.shorten(name)}'' ${stats(name)}").mkString(" | ")
}

object Statistic {
	val names = List("strength", "defense", "wisdom", "agility", "stamina", "spirit")

	def shorten(name: String) = name.substring(0, 3).toUpperCase()
}