package io.arkeus.sword.chat.command

import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.Message
import io.arkeus.sword.util.Logger
import io.arkeus.sword.chat.command.router.Router
import io.arkeus.sword.chat.command.impl.User

object CommandRouter extends Logger {
	val router = new Router(List(
		("user $name:String", User.Profile),
		("user", User.Self)
	))
	
	def execute(user:SwordUser, message:String, router:Router = router) = {
		val route = router.route(message)
		if (route != null) {
			val parameters = route.parameterize(message)
			route.command.execute(user, parameters)
		} else {
			user.send(s"Unknown command '$message', type 'help' for help.")
		}
	}
}