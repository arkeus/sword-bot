package io.arkeus.sword.chat.command.router

import scala.collection.mutable.HashMap
import io.arkeus.sword.chat.command.Command
import scala.collection.mutable.ListBuffer
import io.arkeus.sword.chat.command.router.RouteHelper._
import io.arkeus.sword.user.SwordUser

class Router(val routeList:List[_ <: (String, Command)]) {
	val routes = build
	
	def route(path:String):Route = {
		routes.get(path.alias) match {
			case Some(list) => {
				val sortedRoutes = list.filter(_.matches(path)).sortWith(_.score > _.score)
				if (sortedRoutes.length > 0) sortedRoutes(0) else null
			}
			case None => null
		}
	}
	
	def build:HashMap[String, ListBuffer[Route]] = {
		val map = new HashMap[String, ListBuffer[Route]]
		for (route <- routeList) {
			val path = route._1
			val command = route._2
			val alias = path.alias
			if (map.contains(alias)) {
				map(alias) += new Route(route)
			} else {
				map.put(alias, ListBuffer[Route](new Route(route)))
			}
		}
		return map
	}
}