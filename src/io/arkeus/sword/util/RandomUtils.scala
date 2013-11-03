package io.arkeus.sword.util

import scala.util.Random

object RandomUtils {
	val generator = new Random
	
	def random(min:Int, max:Int) = (Math.random() * (max - min + 1)).floor.toInt + min;
}