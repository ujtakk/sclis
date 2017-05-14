package scalalisp

object Eval {
  def apply(env: Env, prog: Prog): Data = evalProg(env, prog)

  private def evalProg(env: Env, prog: Prog): Data = {
    // TODO
    var retval: Data = Nil()
    for (stmt <- prog.stmts) retval = evalStmt(env, stmt)
    retval
  }

  private def evalStmt(env: Env, stmt: Stmt): Data = stmt match {
    case ComUnit(com) => evalExpr(env, com)
    case DefUnit(dfn) => evalDef(env, dfn)
    // case BeginCD
    case _ => Nil()
  }

  private def evalExpr(env: Env, expr: Expr): Data = expr match {
    case Var(id) => env.get(Var(id))
    case Literal(lit) => evalLit(env, lit)
    // case Proc(opr, opd) => 
    case LambExpr(args, body) => Func(args, body)
    // case CondExpr(test, con, alt) => 
    case Assign(va, expr) => env.set(va, expr)
    // case Derive
    case _ => Nil()
  }

  private def evalDef(env: Env, dfn: Def): Data = dfn match {
    case VarDef(va, expr) =>
      val v = evalExpr(expr)
      env.put(va, v)
    case FuncDef(va, args, body) =>
      val f = Func(args, body)
      env.put(va, f)
    // case BeginDef
    case _ => Nil()
  }

  private def evalDrv(env: Env, drv: Drv): Data = drv match {
    // case CondDrv
    // case ConsEls
    // case CaseDrv
    // case CaseEls
    // case AndDrv
    // case OrDrv
    // case LetDrv
    // case LetSDrv
    // case LetRDrv
    // case BgnDrv
    // case DoDrv
    // case DlyDrv
    // case QQDrv
    case _ => Nil()
  }

  private def evalLit(env: Env, lit: Lit): Data = lit match {
    case QuoteLit(d) => Sym(d)
    case BoolLit(b) => Bool(b)
    case NumLit(n) => Num(n)
    case CharLit(c) => Chr(c)
    case StrLit(s) => Str(s)
    case _ => Nil()
  }
}
