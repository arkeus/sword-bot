package io.arkeus.sword.chat.command

import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.Message
import io.arkeus.sword.util.Logger
import io.arkeus.sword.chat.command.router.Router
import io.arkeus.sword.chat.command.impl.UserCommands
import io.arkeus.sword.chat.command.impl.InventoryCommands
import io.arkeus.sword.chat.command.impl.UselessCommands

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
		("battle", null),
		("battle $area:Int", null),
		// Useless
		("look $direction:String", UselessCommands.Look),
		// Let me end list with a , please
		("_", null)
	))

	def execute(user: SwordUser, message: String, router: Router = router) = {
		val route = router.route(message)
		if (route != null) {
			val parameters = route.parameterize(message)
			route.command.execute(user, parameters)
		} else {
			user.send(s"Unknown command ''$message'', type ''help'' for help.")
		}
	}
}