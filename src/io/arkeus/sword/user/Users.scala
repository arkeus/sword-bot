package io.arkeus.sword.user

object Users extends scala.collection.mutable.HashMap[String, SwordUser] {
	def find(name: String): SwordUser = {
		val username = normalize(name)
		var user = get(username).getOrElse(new SwordUser(username))
		if (!contains(username)) {
			user.load
			this.put(username, user)
		}
		return user
	}

	def exists(name: String) = contains(normalize(name))
	def normalize(name: String) = name.toLowerCase()
}