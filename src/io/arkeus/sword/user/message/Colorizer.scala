package io.arkeus.sword.user.message

import org.jibble.pircbot.Colors
import io.arkeus.sword.data.Element

object Colorizer {
	val bracketedColors = Array(Colors.RED, Colors.OLIVE, Colors.GREEN, Colors.BLUE, Colors.MAGENTA, Colors.DARK_GREEN, Colors.DARK_BLUE, Colors.PURPLE)
	var bracketOffset = 0

	def colorize(message: String) = {
		var coloredMessage = message
		for (brackets <- """\[(.*?)\]""".r.findAllIn(message)) {
			coloredMessage = colorizeBrackets(coloredMessage, brackets)
		}
		for (brackets <- """\{(.*?)\}""".r.findAllIn(message)) {
			coloredMessage = colorizeBrackets(coloredMessage, brackets, false)
		}
		for ((key, color) <- colorMap) {
			coloredMessage = coloredMessage.replaceAll("<:" + key + ">", color)
		}
		coloredMessage = coloredMessage.replaceAll("<:>", Colors.NORMAL)
		formatTags(coloredMessage)
	}

	def colorizeBrackets(message: String, brackets: String, boldTitle: Boolean = true) = {
		val color = bracketedColors(bracketOffset % bracketedColors.length)
		bracketOffset += 1
		val inner = brackets.substring(1, brackets.length - 1)
		val split = inner.indexOf(" ")
		val alias = if (split < 0) inner else inner.substring(0, split)
		val rest = if (split < 0) "" else inner.substring(split)
		val coloredBrackets = Colors.BOLD + color + "[" + Colors.NORMAL + (if (boldTitle) Colors.BOLD + alias + Colors.BOLD else alias ) + rest + Colors.BOLD + color + "]" + Colors.NORMAL
		message.replace(brackets, coloredBrackets)
	}

	def formatTags(message: String) = message.replaceAll("""''(.*?)''""", Colors.BOLD + "$1" + Colors.BOLD)
	
	val colorMap = Map("pink" -> Colors.MAGENTA, "gray" -> Colors.LIGHT_GRAY, "black" -> Colors.BLACK, "red" -> Colors.RED, "green" -> Colors.GREEN, "blue" -> Colors.BLUE, "yellow" -> Colors.OLIVE)
}

trait Colorfier {
	def ce(str:String, e:Element.Value) = s"${elementMap(e)}$str${Colors.BLACK}"
		
	val elementMap = Map(Element.Physical -> Colors.BLACK, Element.Fire -> Colors.RED, Element.Water -> Colors.BLUE, Element.Earth -> Colors.GREEN, Element.Air -> Colors.OLIVE)
}