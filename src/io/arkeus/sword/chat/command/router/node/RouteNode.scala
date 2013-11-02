package io.arkeus.sword.chat.command.router.node

abstract class RouteNode {
	def matches(token:String):Boolean
}