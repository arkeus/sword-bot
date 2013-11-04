package io.arkeus.sword.activity.battle

import io.arkeus.sword.util.Database

object AreaDatabase extends Database[Area] {
	def build(list: List[(String, Int, List[String])]) = {
		var id = 1
		for (info <- list) {
			val name = info._1
			val level = info._2
			val monsters = info._3.map(new Monster(_, level))
			val area = new Area(id, name, monsters)
			ids += area
			names.put(name, area)
			id += 1
		}
	}
	
	val areas = List(
		("Rinau Fields", 1, List("Field Rat", "Black Crow", "Field Golem", "Skeleton Rabbit", "Skeletal Horse")),
		("Rinau Outskirts", 8, List("Skeleton Cat", "Field Boar", "Grass Elemental", "Young Goblin", "Black Raven", "Skeleton Jaguar")),
		("Sazaru Forest", 15, List("Skeleton Spider", "Forest Slime", "Forest Goblin", "Forest Wolf Cub", "Forest Boar", "Skeleton Tarantula")),
		("Moltai Mountainside", 23, List("Mountain Spider", "Mountain Slime", "Mountain Eagle", "Small Earth Golem", "Mountain Wolf Cub", "Giant Skeleton Bat")),
		("Moltai Caves", 31, List("Rock Elemental", "Cave Goblin", "Giant Bat", "Mountain Wolf", "Earth Golem", "Moltai Wyrm")),
		("Moltai Volcano", 39, List("Skeleton Fire Golem", "Charred Wolf Cub", "Skeleton Emberwolf", "Burning Elemental", "Skeleton Flame Slime", "Skeletal Lava Dragon")),
		("Rinau Sewers", 48, List("Water Elemental", "Sewer Slime", "Skeleton Sewer Rat", "Giant Sewer Rat", "Small Water Golem", "Sewer Snake", "Skeletal Sewer Wyrm")),
		("Moltai Peak", 57, List("Small Frost Golem", "Skeleton Frost Wolf", "Ice Boar", "Frost Goblin", "Snow Wolf Cub", "Frost Elemental", "Skeletal Frostwyrm")),
		("Alaer Path", 66, List("Lesser Ogre", "Lesser Manticore", "Lesser Wind Roc", "Forest Sprite", "Field Gnoll", "Skeleton Wolverine", "Skeleton Great Boar")),
		("Alaer Outskirts", 75, List("Ogre", "Manticore", "Field Scaledragon", "Small Lizard", "Field Pixie", "Skeleton Snake", "Skeleton Basilisk")),
		("Alaer Valley", 84, List("Greater Ogre", "Greater Manticore", "Rock Sprite", "Hardened Lizard", "Valley Gnoll", "Skeleton Scaleback", "Skeleton Scaledragon")),
		("Alaer Desert", 93, List("Flame Sprite", "Camel", "Scalded Lizard", "Fire Pixie", "Desert Gnoll", "Skeleton Coyote", "Skeleton Camel")),
		("Alaer Oasis", 102, List("Pond Lizard", "Oasis Nymph", "Earth Scaledragon", "Oasis Sprite", "Water Pixie", "Skeleton Crocodile", "Skeleton Harpy")),
		("Paria Highlands", 111, List("Wind Scaledragon", "Lesser Falcon", "Wind Roc", "Wind Sprite", "Lesser Harpy", "Skeleton Vulture", "Skeleton Owlbear")),
		("Paria Cliffs", 120, List("Cliff Eagle", "Cliff Nymph", "Greater Falcon", "Greater Wind Roc", "Wind Lizard", "Greater Harpy", "Skeleton Stonebeak", "Skeleton Roc")),
		("Paria Coast", 129, List("Coast Nymph", "Water Scaledragon", "Coast Eagle", "Coast Naga", "Coast Gnoll", "Skeleton Stonejaw", "Skeleton Naga")),
		("Outer Paria Isle", 138, List("Isle Nymph", "Lesser Gorilla", "Ocean Roc", "Water Sprite", "Sea Lizard", "Skeleton Sea Turtle", "Skeleton Gorilla")),
		("Inner Paria Isle", 147, List("Tropic Sprite", "Island Roc", "Island Stonejaw", "Greater Gorilla", "Large Crab", "Sea Turtle", "Skeleton Leviathan")),
		("Drigil Lava Fields", 156, List("Flaming Dire Ape", "Red Mastadon", "Fiery Wraith", "Firelight Lynx", "Blazing Ooze", "Red Dragonkin", "Lavamaw")),
		("Drigil Rapids", 165, List("River Troll", "Blue Mastadon", "Tsunami Wraith", "Waterlight Lynx", "Puddle Ooze", "Blue Dragonkin", "Seamaw")),
		("Drigil Caverns", 174, List("Dark Ooze", "Shadow Wraith", "Moonbeast", "Moonlight Lynx", "Black Mastadon", "Black Dragonkin", "Shadowmaw")),
		("Drigil Canyon", 173, List("Hurricane Ooze", "Cliff Troll", "Tornado Wraith", "Airlight Lynx", "White Mastadon", "White Dragonkin", "Windmaw")),
		("Drigil Crags", 182, List("Maenad", "Quake Ooze", "Crag Wraith", "Rocklight Lynx", "Green Mastadon", "Green Dragonkin", "Spiremaw")),
		("Spectral Forest", 191, List("Stargate Archon", "Celestial Ooze", "Heaven Wraith", "Starlight Lynx", "Yellow Mastadon", "Yellow Dragonkin", "Starmaw")),
		("Raythen Lake", 200, List("Waterfall Cyclops", "Watertouched Chimera", "Lake Viper", "Water Naga", "Tsunami Phoenix", "Sea Drake", "Whirlpool Dragonkin", "Seablue Dragon")),
		("Raythen Peaks", 209, List("Windtouched Chimera", "Wind Viper", "Gust Naga", "Tornado Phoenix", "Wind Drake", "Cloud Dragonkin", "Skywhite Dragon")),
		("Raythen Firelands", 218, List("Firetouched Chimera", "Lava Viper", "Lava Naga", "Fiery Phoenix", "Burning Drake", "Flame Dragonkin", "Flamered Dragon")),
		("Raythen Deep", 227, List("Dirt Viper", "Crag Naga", "Rock Phoenix", "Terrestial Drake", "Earthen Dragonkin", "Earthtouched Chimera", "Earthgreen Dragon"))
	)
	
	build(areas)
}