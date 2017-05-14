package scalalisp

class Env(initEnv: Map[Var, Data] = Map[Var, Data]()) {
  val builtins: Map[Var, Data] = Map[Var, Data](
    Var("nil") -> Nil(),
    Var("#t") -> Bool(true),
    Var("#f") -> Bool(false)
  )

  var globalEnv: Map[Var, Data] = builtins ++ initEnv

  def set(id: Var, data: Data) = globalEnv += (id -> data)

  def get(id: Var): Data = globalEnv(id)

  def reset() = globalEnv = builtins ++ initEnv
}
