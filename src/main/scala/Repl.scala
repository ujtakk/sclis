package scalalisp

class Repl(initEnv: Env = new Env()) {
  var myEnv = initEnv

  // more formal function: start from the source
  // def apply(prog: String)
  def apply(prog: Prog) = Print(Eval(myEnv, prog))
}
