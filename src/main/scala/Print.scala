package scalalisp

object Print {
  def apply(v: Val) = print(v)

  private def print(v: Val) = v match {
    case UnitV() => println(())
    case BoolV(b) => println(b)
    case IntV(n) => println(n)
  }
}
