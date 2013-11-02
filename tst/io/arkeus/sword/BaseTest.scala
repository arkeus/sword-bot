package io.arkeus.sword

import org.junit.Assert

trait BaseTest {
	// Intercept based on scala test's, but with overloaded messages
	def intercept[T <: AnyRef](f: => Any)(implicit manifest:Manifest[T]):T = {
		intercept("Expected Exception")(f)
	}
	
	def intercept[T <: AnyRef](message:String)(f: => Any)(implicit manifest:Manifest[T]):T = {
		val klass = manifest.erasure.asInstanceOf[Class[T]]
		
		val caught = try {
			f
			None
		} catch {
			case thrown:Throwable =>  {
				if (!klass.isAssignableFrom(thrown.getClass)) {
					throw new AssertionError(s"$message (Invalid exception raised: ${thrown.getClass.getName})", thrown)
				} else {
					Some(thrown)
				}
			}
		}
		
		caught match {
			case None => throw new AssertionError(s"$message (No exception raised)")
			case Some(thrown) => thrown.asInstanceOf[T]
		}
	}
}