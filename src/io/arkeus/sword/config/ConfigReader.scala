package io.arkeus.sword.config

import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import org.xml.sax.helpers.NewInstance
import org.w3c.dom.Element
import scala.collection.mutable.HashSet

class ConfigReader(val configFile: File) {
	private var document = buildConfig

	def buildConfig = {
		if (!configFile.exists() || !configFile.isFile || !configFile.canRead) {
			throw new IllegalArgumentException("Must pass valid config file to Sword Bot")
		}
		val documentBuilderFactory = DocumentBuilderFactory.newInstance
		val documentBuilder = documentBuilderFactory.newDocumentBuilder
		documentBuilder.parse(configFile)
	}

	def generate: Config = {
		val config = new Config

		config.nick = getString("nick", null)
		config.password = getString("password", "")
		config.server = getString("server", null)
		config.channel = getString("channel", null)
		config.verbose = getBoolean("verbose", false)
		config.login = getString("login", "SwordBot")
		config.prefix = getString("prefix", ";")
		config.dataDirectory = getString("dataDirectory", "data")
		config.administrators = getAdministrators

		return config
	}

	private def getBoolean(tagName: String, defaultValue: Boolean): Boolean = {
		return java.lang.Boolean.valueOf(getValue(tagName, defaultValue))
	}

	private def getString(tagName: String, defaultValue: String): String = {
		return getValue(tagName, defaultValue).toString
	}

	private def getValue(tagName: String, defaultValue: Any): String = {
		val element = getElement(tagName)
		if (element == null) {
			if (defaultValue == null) {
				throw new IllegalArgumentException(s"Expected config value '$tagName' to be defined")
			}
			return defaultValue.toString
		}
		return element.getTextContent
	}

	private def getElement(tagName: String): Element = {
		val nodes = document.getElementsByTagName(tagName)
		val length = nodes.getLength()
		if (length == 0) {
			return null
		}
		if (length != 1) {
			throw new IllegalArgumentException(s"Expected exactly one config value for tag '$tagName', found $length")
		}
		return nodes.item(0).asInstanceOf[Element]
	}
	
	private def getAdministrators = {
		val set = new HashSet[Administrator]
		
		val element = getElement("administrators")
		if (element != null) {
			val nodes = element.getElementsByTagName("administrator")
			for (i <- 0 to nodes.getLength() - 1) {
				val node = nodes.item(i).asInstanceOf[Element]
				val alias = if (node.hasAttribute("alias")) node.getAttribute("alias") else null
				val loginNode = node.getElementsByTagName("adminLogin")
				val login = if (loginNode.getLength() == 1) loginNode.item(0).getTextContent else null
				val hostnameNode = node.getElementsByTagName("adminHostname")
				val hostname = if (hostnameNode.getLength() == 1) hostnameNode.item(0).getTextContent else null
				set += new Administrator(alias, login, hostname)
			}
		}
		
		println("ADMINS: " + set)
		
		new Administrators(set)
	}
}