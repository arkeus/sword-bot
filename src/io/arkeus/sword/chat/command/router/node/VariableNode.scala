package io.arkeus.sword.chat.command.router.node

import io.arkeus.sword.chat.command.router.parse.TypeParser

class VariableNode(val token:String) extends RouteNode {
	val (name, klass) = parse(token)
	
	override def matches(token:String):Boolean = TypeParser.validate(token, klass)
	
	def parameterize(token:String):(String, Any) = {
		TypeParser.parse(token, klass) match {
			case Some(parsed) => (name, parsed)
			case None => throw new IllegalArgumentException(s"Could not parse token $token")
		}
	}
	
	private def parse(token:String):(String, String) = {
		val tokens = token.drop(1).split(":")
		if (tokens.length != 2) {
			throw new IllegalArgumentException(s"Invalid route token: $token")
		}
		if (!isSupportedClass(tokens(1))) {
			throw new IllegalArgumentException(s"Unsupported route variable type: ${tokens(1)}")
		}
		return (tokens(0), tokens(1))
	}
	
	private def isSupportedClass(token:String):Boolean = {
		return List("String", "Int").contains(token)
	}
}