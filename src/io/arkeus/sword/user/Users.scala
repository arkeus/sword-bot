package io.arkeus.sword.user

import scala.collection.mutable.HashMap

object Users extends scala.collection.mutable.HashMap[String, SwordUser] {
	var administrators = null
	val active = new HashMap[String, SwordUser]
	
	def find(name: String): SwordUser = {
		val username = normalize(name)
		var user = get(username).getOrElse(new SwordUser(username))
		if (!contains(username)) {
			val loaded = user.load
			if (!loaded) {
				user.initialize
			}
			this.put(username, user)
		}
		return user
	}
	
	def setActive(user: SwordUser) = active.put(user.name, user)
	def setInactive(user: SwordUser) = active.remove(user.name)
	def isActive(username: String) = active.contains(username.toLowerCase)
	def activeUsers = active.values

	def exists(name: String) = contains(normalize(name))
	def normalize(name: String) = name.toLowerCase()
}