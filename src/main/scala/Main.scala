package scalalisp

object Main {
  import scalalisp.Env
  import scalalisp.Eval
  import scalalisp.Print

  def main(args: Array[String]) = {
    val myEnv = new Env()

    myEnv.put(Var("a"), Bool(false))
    println(myEnv.get(Var("a")))
    myEnv.reset()
    println(myEnv.get(Var("nil")))
  }
}
