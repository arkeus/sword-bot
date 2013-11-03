package io.arkeus.sword.chat.command.impl

import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.command.router.Parameters
import io.arkeus.sword.activity.battle.AreaDatabase

object BattleCommands {
	object Areas extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			user.send("Choose which area you would like to explore. Use ''battle $id'' to choose.")
			for (area <- AreaDatabase.all) {
				user.send(s"[${area.id}] ${area.name} (Average Monster Level: ${area.level})")
			}
		}
	}
	
	object Battle extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			val areaId = params.int("area")
			val area = AreaDatabase.byId(areaId)
			user.battle(area)
		}
	}
}