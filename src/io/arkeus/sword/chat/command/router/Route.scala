package io.arkeus.sword.chat.command.router

import io.arkeus.sword.chat.command.UserCommand
import io.arkeus.sword.chat.command.impl.User

class Route(path:String) {
	val router = List(
		("user $name:String", User.Profile),
		("user", User.Self)
	)
}