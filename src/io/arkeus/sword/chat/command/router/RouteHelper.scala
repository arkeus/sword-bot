package io.arkeus.sword.chat.command.router

object RouteHelper {
	implicit class Path(str: String) {
		def alias: String = {
			return str.split(" ")(0)
		}
	}
}