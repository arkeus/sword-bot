package io.arkeus.sword.chat.command.router.node

import io.arkeus.sword.chat.command.Command

class RootNode extends Node {
	def build(routeList:List[_ <: (String, Command)]):RootNode = {
		for (route:(String, Command) <- routeList) {
			add(route._1, route._2)
		}
		return this
	}
}