package io.arkeus.sword.user.item

object PropertyContainer {
	def keyset(sets: { def keys: Set[String] }*) = sets.filter(_ != null).foldLeft(Set[String]()) { (a, b) => a ++ b.keys }
}