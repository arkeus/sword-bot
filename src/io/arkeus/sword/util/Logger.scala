package io.arkeus.sword.util

import org.apache.logging.log4j.LogManager

trait Logger {
	lazy val logger = LogManager.getLogger(this.getClass)
}