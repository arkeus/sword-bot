package io.arkeus.sword.chat.command.router

object RouteHelper {
	implicit class Path(str: String) {
		def alias = str.tokenize(0)		
		def tokenize = str.split("""\s+""")
	}
}