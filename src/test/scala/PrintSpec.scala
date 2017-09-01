package scalalisp

import org.scalatest._

class PrintSpec extends FlatSpec with Matchers {
  "Print show" should "print Nil as ()" in {
    Print.show(Nil()) shouldBe "()"
  }

  it should "print bool value with symbol #t or #f" in {
    Print.show(Bool(true)) shouldBe "#t"
    Print.show(Bool(false)) shouldBe "#f"
  }

  it should "print list as (a b c d) form" in {
    val l: List[Data] = List(Num(1), Num(2), Num(3), Num(4))
    Print.show(Lst(l)) shouldBe "(1 2 3 4)"
  }

  it should "use closure" in {
    val myEnv = new Env()

    myEnv.put(Var("a"), Bool(false))

    myEnv.get(Var("a")) shouldBe Bool(false)
    Print.show(myEnv.get(Var("a"))) shouldBe "#f"
  }

  it should "print char correctly" in {
    Print.show(Chr('c')) shouldBe "#\\c"
  }
}
