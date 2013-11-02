package io.arkeus.sword.chat.command.router

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers._
import org.junit.Test
import io.arkeus.sword.chat.command.Command
import io.arkeus.sword.user.SwordUser
import io.arkeus.sword.chat.command.CommandRouter
import io.arkeus.sword.BaseTest

class RouterTest extends BaseTest {
	@Test
	def commandRouterTest = {
		val router = new Router(List(
			("buy $num:Int $item-id:Int", TestBuyCommand),
			("buy $num:Int $item:String", TestBuyCommand),
			("profile $user:String", TestProfileCommand)
		))
		
		val buyParams = CommandRouter.execute(null, "buy 1 sword", router).asInstanceOf[Parameters]
		assertThat(buyParams.int("num"), is(1))
		assertThat(buyParams.string("item"), is("sword"))
		intercept[ClassCastException] { buyParams.int("item") }
		
		val buyIntParams = CommandRouter.execute(null, "buy 1 5", router).asInstanceOf[Parameters]
		assertThat(buyIntParams.int("num"), is(1))
		assertThat(buyIntParams.int("item-id"), is(5))

		val profileParams = CommandRouter.execute(null, "profile arkeus", router).asInstanceOf[Parameters]
		assertThat(profileParams.string("user"), is("arkeus"))
		
		val invalid = CommandRouter.execute(new SwordUser("Arkeus"), "win", router)
	}
	
	@Test
	def commandRouterTestOrderRequirement = {
		val router = new Router(List(
			("buy $num:Int $item:String", TestBuyCommand),
			("buy $num:Int $item-id:Int", TestBuyCommand)
		))
		
		val buyIntParams = CommandRouter.execute(null, "buy 1 5", router).asInstanceOf[Parameters]
		intercept[NoSuchElementException]("ints can be parsed as string, so that route matches and gets precendence") {
			assertThat(buyIntParams.int("item-id"), is(5))
		}
		assertThat(buyIntParams.string("item"), is("5"))
		assertThat(buyIntParams.int("num"), is(1))
	}
	
	object TestBuyCommand extends Command {
		override def execute(user:SwordUser, params:Parameters):Any = {
			return params
		}
	}
	
	object TestProfileCommand extends Command {
		override def execute(user:SwordUser, params:Parameters):Any = {
			return params
		}
	}
}