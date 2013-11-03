package io.arkeus.sword.activity.battle.reward

import io.arkeus.sword.user.item.Item

class Reward

class GoldReward(val amount: Int) extends Reward
class ExperienceReward(val amount: Int) extends Reward
class ItemReward(val item: Item) extends Reward