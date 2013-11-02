package io.arkeus.sword.chat.command.router.node

class ConstantNode(val token: String) extends RouteNode {
	override def matches(incomingToken: String): Boolean = {
		return incomingToken == token
	}
}