package scalalisp

object Main {
  import scalalisp.Eval
  import scalalisp.Print

  def main(args: Array[String]) = {
    val x1 = IntLit(12)
    val x2 = IntLit(23)
    val v = Eval(BinOp(Add(), x1, x2))
    Print(v)
    Print(Eval(BinOp(Or(), BoolLit(false), BoolLit(true))))
  }
}
