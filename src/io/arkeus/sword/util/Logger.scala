package io.arkeus.sword.util

import org.apache.logging.log4j.LogManager

trait Logger {
	val logger = LogManager.getLogger(this.getClass)
}