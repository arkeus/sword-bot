package io.arkeus.sword.chat.command.router

import scala.collection.mutable.HashMap
import io.arkeus.sword.chat.command.Command
import scala.collection.mutable.ListBuffer
import io.arkeus.sword.chat.command.router.RouteHelper._
import io.arkeus.sword.user.SwordUser

class Router(val routeList: List[Any], val shortcuts: Map[String, String]) {
	val routes = build

	def aliases = routes.keys.toList.filter(alias => routes(alias).count(route => route.command != null && !route.command.hidden) > 0)
	def all = routes.values.flatten.filter(!_.command.hidden)
	def where(alias: String) = routes.get(alias) match { case Some(routes) => routes.filter(!_.command.hidden) case None => null }

	def route(path: String) = {
		val truePath = mapShortcuts(path)
		routes.get(truePath.alias) match {
			case Some(list) => {
				val sortedRoutes = list.filter(_.matches(truePath)).sortWith(_.score > _.score)
				if (sortedRoutes.length > 0) sortedRoutes(0) else null
			}
			case None => null
		}
	}
	
	def mapShortcuts(path: String) = {
		val alias = path.alias
		val tokens = path.tokenize
		if (shortcuts.contains(alias)) {
			(Array(shortcuts(alias)) ++ tokens.drop(1)).mkString(" ")
		} else {
			path
		}
	}

	def build = {
		val map = new HashMap[String, ListBuffer[Route]]
		// We cast here because scala keeps complaining randomly
		for (route <- routeList.asInstanceOf[List[(String, _ <: Command)]]) {
			val path = route._1
			val command = route._2
			val alias = path.alias
			if (map.contains(alias)) {
				map(alias) += new Route(route)
			} else {
				map.put(alias, ListBuffer[Route](new Route(route)))
			}
		}
		map
	}
}