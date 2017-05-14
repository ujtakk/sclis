package scalalisp

class Env(initEnv: Map[Var, Data] = Map[Var, Data]()) {
  import scala.collection.mutable.Map
  import scalalisp.Print

  val builtins: Map[Var, Data] = Map[Var, Data](
    Var("nil") -> Nil(),
    Var("#t") -> Bool(true),
    Var("#f") -> Bool(false)
  )

  var globalEnv: Map[Var, Data] = builtins ++ initEnv

  def put(id: Var, data: Data): Data = {
    globalEnv.update(id, data)
    Sym(id.id)
  }

  def get(id: Var): Data = {
    val prev = globalEnv.get(id)
    prev match {
      case Some(v) => v
      case None =>
        Print.panic("Unbound Value: "+id.id)
    }
  }

  def set(id: Var, data: Data): Data = {
    val prev = globalEnv.put(id, data)
    prev match {
      case Some(v) => v
      case None =>
        globalEnv.remove(id)
        Print.panic("Unbound Value: "+id.id)
    }
  }

  def del(id: Var): Data = {
    val prev = globalEnv.remove(id)
    prev match {
      case Some(v) => v
      case None =>
        Print.panic("Unbound Value: "+id.id)
    }
  }

  def reset() = globalEnv = builtins ++ initEnv
}
