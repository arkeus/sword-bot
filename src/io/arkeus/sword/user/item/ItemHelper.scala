package io.arkeus.sword.user.item

import scala.collection.immutable.HashSet

object ItemHelper {
	implicit class NullPropertyContainer(nope: Null) {
		def keys = HashSet[String]()
	}
}