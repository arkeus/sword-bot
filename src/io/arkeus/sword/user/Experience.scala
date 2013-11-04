package io.arkeus.sword.user

class Experience(var level: Int = 1, var current: Int = 0, var experienceFormula: (Int) => Int = null) {
	experienceFormula = if (experienceFormula == null) defaultExperienceFormula else experienceFormula
	var max = experienceFormula(level)

	def gain(amount: Int) = {
		var leveledUp = false
		current += amount
		while (current > max) {
			level += 1
			current -= max
			max = experienceFormula(level)
			leveledUp = true
		}
		leveledUp
	}
	
	def load(level: Int, current: Int) = {
		this.level = level
		this.current = current
		max = experienceFormula(level)
	}

	private def defaultExperienceFormula(level: Int): Int = (25 + 10 * (level - 1) + Math.pow(level - 1, 1.5)).toInt
}