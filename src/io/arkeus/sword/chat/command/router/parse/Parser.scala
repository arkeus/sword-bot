package io.arkeus.sword.chat.command.router.parse

abstract class Parser[T] {
	protected def convert(token:String):Option[T]
	
	def parse(token:String):Option[T] = {
		try {
			convert(token)
		} catch {
			case _:Throwable => None
		}
	}
	
	def validate(token:String):Boolean = {
		try {
			convert(token)
			return true
		} catch {
			case _:Throwable => return false
		}
	}
}

object StringParser extends Parser[String] {
	protected def convert(token:String) = Some(token)
}

object IntParser extends Parser[Int] {
	protected def convert(token:String) = Some(token.toInt)
}

object DoubleParser extends Parser[Double] {
	protected def convert(token:String) = Some(token.toDouble)
}

object TypeParser {
	def parse(token:String, klass:String):Option[Any] = parser(klass).parse(token)
	def validate(token:String, klass:String):Boolean = parser(klass).validate(token)
	
	private def parser(klass:String) = klass match {
		case "String" => StringParser
		case "Int" => IntParser
		case "Double" => DoubleParser
	}
}