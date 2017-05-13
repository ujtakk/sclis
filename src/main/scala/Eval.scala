package scalalisp

object Eval {
  def apply(exp: Expr): Val = eval(exp)

  private def panic(msg: String): UnitV = {
    println(msg)
    UnitV()
  }

  private def apply(op: Op, x1: Val, x2: Val): Val = (op, x1, x2) match {
    case (Add(), IntV(v1), IntV(v2)) => IntV(v1 + v2)
    case (Add(), _, _) => panic("Both arguments must be integer: +")
    case (Sub(), IntV(v1), IntV(v2)) => IntV(v1 - v2)
    case (Sub(), _, _) => panic("Both arguments must be integer: +")
    case (Or(), BoolV(v1), BoolV(v2)) => BoolV(v1 || v2)
    case (Or(), _, _) => panic("Both arguments must be boolean: ||")
    case (And(), BoolV(v1), BoolV(v2)) => BoolV(v1 && v2)
    case (And(), _, _) => panic("Both arguments must be boolean: &&")
  }

  private def eval(exp: Expr): Val = exp match {
    case BoolLit(b) => BoolV(b)
    case IntLit(i) => IntV(i)
    case BinOp(op, x1, x2) =>
      val v1 = eval(x1)
      val v2 = eval(x2)
      apply(op, v1, v2)
  }
}
