package io.arkeus.sword

import io.arkeus.sword.config.ConfigReader
import java.io.File

object SwordMain {
	def main(args: Array[String]) = {
		val config = new ConfigReader(new File(args(0))).generate
		val sword = new Sword(config)
		sword.initialize
	}
}