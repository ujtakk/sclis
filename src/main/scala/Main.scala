package scalalisp

object Main {
  import scalalisp.Env
  import scalalisp.Eval
  import scalalisp.Print

  def main(args: Array[String]) = {
    val myEnv = new Env()

    myEnv.put(Var("a"), Bool(false))
    Print(myEnv.get(Var("a")))
    Print(Chr('c'))
    Print(Clos(myEnv, List(Var("car"), Var("cdr")), (x: List[Data]) => Nil()))
    myEnv.reset()
    Print(myEnv.get(Var("nil")))
    Print(Eval(myEnv,
      Prog(List(ComUnit(
        ProcCall(VarExpr(Var("+")),
                 List(Literal(NumLit(12)), Literal(NumLit(43)))))))))
    // Print(Read())
  }
}
