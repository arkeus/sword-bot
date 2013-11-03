package io.arkeus.sword.chat.command.router

import io.arkeus.sword.chat.command.impl.UserCommands
import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.chat.command.router.node.RouteNode
import scala.collection.mutable.ListBuffer
import io.arkeus.sword.chat.command.router.node.ConstantNode
import io.arkeus.sword.chat.command.router.node.VariableNode
import io.arkeus.sword.chat.command.router.RouteHelper._
import scala.collection.mutable.HashMap
import io.arkeus.sword.user.SwordUser

class Route(routeInfo: (String, Command)) {
	val path = routeInfo._1
	val alias = path.alias
	val command = routeInfo._2
	val nodes = build(routeInfo._1)
	val score = calculateScore

	def matches(path: String): Boolean = {
		val tokens = path.tokenize
		var i = 0
		for (node <- nodes) {
			if (tokens.length <= i || !node.matches(tokens(i))) {
				return false
			}
			i += 1
		}
		return true
	}

	def parameterize(path: String): Parameters = {
		val tokens = path.tokenize
		val params = new HashMap[String, Any]
		for (nodeInfo <- nodes.zip(tokens)) {
			if (nodeInfo._1.isInstanceOf[VariableNode]) {
				val node: VariableNode = nodeInfo._1.asInstanceOf[VariableNode]
				val token: String = nodeInfo._2
				val (key, value) = node.parameterize(token)
				params.put(key, value)
			}
		}
		return new Parameters(params.toMap)
	}

	private def calculateScore: Int = {
		return nodes.count(_.isInstanceOf[ConstantNode])
	}

	private def build(path: String): ListBuffer[RouteNode] = {
		val list = ListBuffer[RouteNode]()
		var pathNodes = path.tokenize.toList

		for (pathNode <- pathNodes) {
			list += createRouteNode(pathNode)
		}

		return list
	}

	private def createRouteNode(token: String): RouteNode = {
		token.charAt(0) match {
			case '$' => new VariableNode(token)
			case _ => new ConstantNode(token)
		}
	}
}