package io.arkeus.sword.config

import java.io.File
import scala.collection.mutable.HashSet

class Config(
	var nick: String = null,
	var password: String = null,
	var server: String = null,
	var channel: String = null,
	var verbose: Boolean = false,
	var login: String = null,
	var prefix: String = null,
	var dataDirectory: String = null,
	var administrators: Administrators = null)