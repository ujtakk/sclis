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
    Print(Clos(myEnv, List(Var("car"), Var("cdr")), (x: List[Var]) => Nil()))
    myEnv.reset()
    Print(myEnv.get(Var("nil")))
  }
}
