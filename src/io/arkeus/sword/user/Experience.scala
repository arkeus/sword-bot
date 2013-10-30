package io.arkeus.sword.user

class Experience(var level:Int = 1, var current:Int = 0, var experienceFormula:(Int) => Int = null) {
	experienceFormula = if (experienceFormula == null) defaultExperienceFormula else experienceFormula
	var max = experienceFormula(level)
	
	def gain(amount:Int) = {
		current += amount
		while (current > max) {
			level += 1
			current -= max
			max = experienceFormula(level)
		}
	}
	
	private def defaultExperienceFormula(level:Int):Int = (100 + 10 * level + Math.pow(level, 1.1)).toInt
}