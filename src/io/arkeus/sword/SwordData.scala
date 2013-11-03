package io.arkeus.sword

import java.io.File
import io.arkeus.sword.util.Logger
import io.arkeus.sword.config.Config
import io.arkeus.sword.user.SwordUser
import org.apache.commons.io.FileUtils

object SwordData extends Logger {
	private var directory: File = null
	private var usersDirectory: File = null
	private var configFile: File = null

	def saveUser(user: SwordUser) = {
		val userFile = new File(usersDirectory, s"${user.name}.json")
		if (!userFile.exists()) {
			userFile.createNewFile
		}
		FileUtils.write(userFile, user.serialize)
	}

	def loadUser: SwordUser = {
		null
	}

	def initialize(config: Config) = {
		directory = new File(config.dataDirectory)
		usersDirectory = new File(directory, "users")
		configFile = new File(directory, "config")

		if (!directory.exists) {
			logger.info("Data directory doesn't exist, creating...")
			directory.mkdirs
			logger.info(s"Data directory created at ${directory.getAbsolutePath}")
		} else if (!directory.isDirectory) {
			throw new IllegalStateException(s"Invalid file at ${directory.getAbsolutePath}, data directory must be a directory")
		}

		if (!usersDirectory.exists) {
			logger.info("Users directory doesn't exist, creating...")
			usersDirectory.mkdirs
			logger.info(s"Users directory created at ${usersDirectory.getAbsolutePath}")
		} else if (!usersDirectory.isDirectory) {
			throw new IllegalStateException(s"Invalid file at ${usersDirectory.getAbsolutePath}, users directory must be a directory")
		}

		if (!configFile.exists) {
			logger.info("Config file doesn't exist, creating...")
			configFile.createNewFile
			logger.info(s"Config file created at ${configFile.getAbsolutePath}")
		} else if (!configFile.isFile) {
			throw new IllegalStateException(s"Invalid file at ${configFile.getAbsolutePath}, config file must be a file")
		}
	}
}