package io.arkeus.sword.chat.command.impl

import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.command.router.Parameters
import io.arkeus.sword.chat.command.router.RouteHelper._
import io.arkeus.sword.chat.command.CommandRouter

object HelpCommands {
	object Help extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			val route = params.message
			if (route.length < 1) {
				basicHelp(user)
			} else {
				routeHelp(user, route)
			}
		}
		
		private def basicHelp(user: SwordUser) = {
			val aliases = CommandRouter.aliases.mkString(" ")
			user.send("{''The Automated Sword Bot Automated Help Automator''}")
			user.send("The help command can be used to get information about the game.")
			user.send("Use ''help $category'' to get information about a category of commands.")
			user.send(s"Available command categories: $aliases")
		}
		
		private def routeHelp(user: SwordUser, routeString: String) = {
			val alias = routeString.alias
			val routes = CommandRouter.routes(alias)
			if (routes == null) {
				user.send("Invalid category, use ''help'' to see valid help categories")
			} else {
				for (route <- routes) {
					user.send(s"[${route.path.replaceAll(""":\w+""", "")}] ${route.command.help}")
				}
			}
		}
		
		override def help = "Displays the help prompt"
	}
}