package io.arkeus.sword.user.message

import org.jibble.pircbot.Colors

object Colorizer {
	val bracketedColors = Array(Colors.RED, Colors.OLIVE, Colors.GREEN, Colors.DARK_GREEN, Colors.BLUE, Colors.DARK_BLUE)
	var bracketOffset = 0

	def colorize(message: String) = {
		//"""\[(.*?)\]""".r.findAllIn(message).map(colorizeBrackets).mkString(" ")
		var coloredMessage = message
		for (brackets <- """\[(.*?)\]""".r.findAllIn(message)) {
			coloredMessage = colorizeBrackets(coloredMessage, brackets)
		}
		for (brackets <- """\{(.*?)\}""".r.findAllIn(message)) {
			coloredMessage = colorizeBrackets(coloredMessage, brackets, false)
		}
		formatTags(coloredMessage)
	}

	def colorizeBrackets(message: String, brackets: String, boldTitle: Boolean = true) = {
		val color = bracketedColors(bracketOffset % bracketedColors.length)
		bracketOffset += 1
		val inner = brackets.substring(1, brackets.length - 1)
		val split = inner.indexOf(" ")
		println("SPLIT " + split)
		val alias = if (split < 0) inner else inner.substring(0, split)
		val rest = (if (split < 0) "" else inner.substring(split))
		val coloredBrackets = Colors.BOLD + color + "[" + Colors.NORMAL + (if (boldTitle) Colors.BOLD + alias + Colors.BOLD else alias ) + rest + Colors.BOLD + color + "]" + Colors.NORMAL
		message.replace(brackets, coloredBrackets)
	}

	def formatTags(message: String) = message.replaceAll("""''(.*?)''""", Colors.BOLD + "$1" + Colors.BOLD)
}