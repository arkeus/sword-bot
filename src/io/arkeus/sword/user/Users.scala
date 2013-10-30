package io.arkeus.sword.user

object Users extends scala.collection.mutable.HashMap[String, SwordUser] {
	def find(name:String):SwordUser = {
		val username = normalize(name)
		var user = get(username).getOrElse(new SwordUser(username))
		if (!contains(username)) {
			this.put(username, user)
		}
		return user
	}
	
	def normalize(name:String) = name.toLowerCase()
}