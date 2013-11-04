package io.arkeus.sword.config

import scala.collection.mutable.HashSet
import io.arkeus.sword.user.SwordUser

class Administrators(val set: HashSet[Administrator]) {
	def isAdministrator(login: String, hostname: String) = {
		set.count(admin => (admin.login == null || admin.login == login) && (admin.hostname == null || admin.hostname == hostname)) >= 1
	}
}