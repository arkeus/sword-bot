package io.arkeus.sword.chat.command

import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.Message
import io.arkeus.sword.util.Logger
import io.arkeus.sword.chat.command.router.Router
import io.arkeus.sword.chat.command.impl.UserCommands
import io.arkeus.sword.chat.command.impl.InventoryCommands
import io.arkeus.sword.chat.command.impl.UselessCommands
import io.arkeus.sword.chat.command.impl.BattleCommands
import io.arkeus.sword.chat.command.impl.ActivityCommands
import io.arkeus.sword.chat.command.impl.HelpCommands
import io.arkeus.sword.chat.command.router.Route

object CommandRouter extends Logger {
	val router = new Router(List(
		// User
		("user $name:String", UserCommands.Profile),
		("user", UserCommands.Self),
		// Inventory
		("inventory $id:Int", InventoryCommands.Inspect),
		("inventory gimme", InventoryCommands.Debug),
		("inventory", InventoryCommands.Show),
		// Equipment
		("equipped $slot:String", InventoryCommands.InspectEquipment),
		("equipped", InventoryCommands.Equipped),
		("equip $id:Int", InventoryCommands.Equip),
		// Battle
		("battle $area:Int", BattleCommands.Battle),
		("battle", BattleCommands.Areas),
		// Activity
		("stop", ActivityCommands.Stop),
		// Help
		("help", HelpCommands.Help),
		// Useless
		("look $direction:String", UselessCommands.Look),
		// Debug
		("quit", UselessCommands.Quit)
	))
	
	def aliases = router.aliases
	
	def routes(alias: String = null) = {
		if (alias == null) {
			router.all
		} else {
			router.where(alias)
		}
	}

	def execute(user: SwordUser, message: String, router: Router = router) = {
		val route = router.route(message)
		if (user == null || user.idle) {
			if (route != null) {
				run(user, message, route)
			} else {
				user.send(s"Unknown command ''$message'', type ''help'' for help.")
			}
		} else {
			if (route != null && route.command != null && route.command.idleable) {
				run(user, message, route)
			}
		}
	}
	
	private def run(user: SwordUser, message: String, route: Route) = {
		val parameters = route.parameterize(message)
		route.command.execute(user, parameters)
	}
}