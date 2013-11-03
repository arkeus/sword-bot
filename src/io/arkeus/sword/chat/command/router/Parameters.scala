package io.arkeus.sword.chat.command.router

class Parameters(val params: Map[String, Any], val message:String) {
	def int(key: String) = params(key).asInstanceOf[Int]
	def string(key: String) = params(key).asInstanceOf[String]
	def double(key: String) = params(key).asInstanceOf[Double]

	override def toString = params.toList.map(e => s"${e._1} => ${e._2}").mkString(", ")
}