package io.arkeus.sword.user.item

import scala.collection.mutable.ListBuffer
import io.arkeus.sword.data.Rarity

class TooltipBuilder(inventoryItem: InventoryItem) {
	val item = inventoryItem.item
	val MIN_WIDTH = 22
	val width = Math.max(MIN_WIDTH, 4 + item.name.length)
	val lines = ListBuffer[String]()
		
	def build = {
		val rarityColor = Rarity.toColor(inventoryItem.rarity)
		addCentered(s"<:$rarityColor>''${item.name}''<:>")
		add(s"${item.itemtype}", s"${item.subinfo}")
		add(s"Level ${item.level}", s"<:gray>0 Slots<:>")
		lines.mkString("\n")
	}
	
	private def add(left: String = "", right: String = "") = {
		val len = 4 + length(left) + length(right)
		val spaces = " " * (width - len)
		lines += s"{ $left$spaces$right }"
	}
	
	private def addCentered(string: String) = {
		val spacesLen = width - (4 + length(string))
		val leftLen = (spacesLen / 2.0).floor.toInt
		val rightLen = (spacesLen / 2.0).ceil.toInt
		val leftSpaces = " " * leftLen
		val rightSpaces = " " * rightLen
		lines += s"{ $leftSpaces$string$rightSpaces }"
	}
	
	private def length(value: String) = if (value != null) value.replaceAll("""\<.*?\>""", "").replaceAll("''", "").length else 0
}