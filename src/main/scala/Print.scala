package scalalisp

object Print {
  def apply(v: Data) = _print(v)

  private def _print(v: Data) = v match {
    case Nil() => println("()")
    case Bool(b) => println(if (b) "#t" else "#f")
    case Num(i) => println(i)
    case Chr(c) => println("#\\"+c)
    case Str(s) => println('"'+s+'"')
    case Sym(s) => println(s)
    case Lst(l) => println(l)
    case DotLst(car, cdr) => println(car, cdr)
    case AbbrLst(d) => println(d)
    case Vec(v) => println(v)
    case Clos(env, args, body) =>
      print("(lambda (")
      print(args.map(x => x.id).mkString(" "))
      println(") ...)")
  }

  def panic(v: String): Data = {
    println(v)
    Nil()
  }
}
