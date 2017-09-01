package scalalisp

object Print {
  def apply(v: Data) = println(show(v))

  def show(v: Data): String = v match {
    case Nil() => "()"
    case Bool(b) => if (b) "#t" else "#f"
    case Num(i) => i.toString()
    case Chr(c) => "#\\"+c
    case Str(s) => '"'+s+'"'
    case Sym(s) => s
    case Lst(l) => "("+unwords(l)+")"
    case DotLst(car, cdr) => "("+unwords(car)+" . "+show(cdr)+")"
    case AbbrLst(d) => "'"+show(d)
    case Vec(v) => "#("+unwords(v)+")"
    case Clos(env, args, body) =>
      "(lambda ("+args.map(x => x.id).mkString(" ")+") ...)"
  }

  private def unwords(l: List[Data]): String =
    l.map(x => show(x)).mkString(" ")
  private def unwords(l: Array[Data]): String =
    l.map(x => show(x)).mkString(" ")

  def panic(v: String): Data = {
    println(v)
    Nil()
  }
}
