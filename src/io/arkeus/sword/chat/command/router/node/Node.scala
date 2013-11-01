package io.arkeus.sword.chat.command.router.node

import scala.collection.mutable.HashMap
import io.arkeus.sword.chat.command.Command

class Node {
	val children = new HashMap[String, Node]
	var path:String = null
	var command:Command = null
	
	def add(path:String, command:Command = null):Node = {
		val (token, rest) = splitPath(path)
		this.path = path
		
		children.get(token) match {
			case Some(node:Node) => {
				if (rest.length > 0) {
					node.add(rest, command)
				} else {
					node.command = command
				}
			}
			case None => {
				val node = new Node
				children.put(token, node)
				if (rest.length > 0) {
					node.add(rest, command)
				} else {
					node.command = command
				}
			}
		}
		
		return this
	}
	
	def route(path:String):Command = {
		if (path.length == 0) {
			return command
		}
		
		val (token, rest) = splitPath(path)
		children.get(token) match {
			case Some(node:Node) => node.route(rest)
			case None => null
		}
	}
	
	def splitPath(path:String):(String, String) = {
		val tokens = path.split(" ").toList
		val token = tokens(0)
		val rest = tokens.drop(1).mkString(" ")
		
		return (token, rest)
	}
	
	def size:Int = {
		return 1 + children.values.map(_.size).sum
	}
	
	override def toString:String = {
		return "(" + children.toList.map((tuple) => s"${tuple._1} => ${tuple._2}").mkString(", ") + ")"
	}
}