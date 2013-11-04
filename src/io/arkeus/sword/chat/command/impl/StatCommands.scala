package io.arkeus.sword.chat.command.impl

import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.command.router.Parameters
import io.arkeus.sword.user.Statistic

object StatCommands {
	object Show extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			user.send(s"[Stats ${user.stats}] {''Stat Points'' ${user.stats.points(user.level)}}")
		}
		
		override def help = "Shows your stats and available stat points"
	}
	
	object Add extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			val stat = params.string("stat")
			if (!Statistic.names.contains(stat)) {
				user.send(s"Invalid stat ''$stat'', must be one of: " + Statistic.names.mkString(" "))
			} else {
				val amount = params.int("amount")
				if (user.stats.points(user.level) < amount) {
					user.send(s"You do not have ''$amount'' stat points to spend")
				} else {
					user.stats.spend(stat, amount)
					user.send(s"You successfully increased your ''$stat'' by ''$amount''!")
				}
			}
		}
		
		override def help = "Adds $amount stat points to $stat (one of " + Statistic.names.mkString(" ") + ")"
	}
	
	object Reset extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			val cost = StatCommands.resetCost(user.level)
			user.send(s"Resetting your stats will cost ''$cost gold''. Use ''stats reset confirm'' to reset.")
		}
	}
	
	object ResetConfirm extends Command {
		override def execute(user: SwordUser, params: Parameters) = {
			val cost = StatCommands.resetCost(user.level)
			if (user.gold < cost) {
				user.send("You do not have the required gold to reset your stats")
			} else {
				user.stats.reset
				user.send("Your stats have been reset!")
			}
		}
	}
	
	def resetCost(level: Int) = 10 + level * 2
}