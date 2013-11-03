package io.arkeus.sword.config

import java.io.File

class Config(
	var nick: String = null,
	var password: String = null,
	var server: String = null,
	var channel: String = null,
	var verbose: Boolean = false,
	var login: String = null,
	var prefix: String = null,
	var dataDirectory: String = null,
	var administrators: Set[String] = null)