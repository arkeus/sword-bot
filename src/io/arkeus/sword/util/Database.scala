package io.arkeus.sword.util

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap

class Database[T] {
	val names = new HashMap[String, T]
	val ids = new ListBuffer[T]
	
	def byName(name: String) = names.get(name)
	def byId(id: Int) = ids(id - 1)
	def all = ids
}