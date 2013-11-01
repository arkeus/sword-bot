package io.arkeus.sword.chat.command.router

import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.chat.command.router.node.Node
import io.arkeus.sword.chat.command.router.node.RootNode

class Router(val routeList:List[_ <: (String, Command)]) {
	val routes = new RootNode().build(routeList)
	
	def route(path:String):Command = routes.route(path)
}