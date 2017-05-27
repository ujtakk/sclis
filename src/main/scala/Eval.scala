package scalalisp

object Eval {
  def apply(env: Env, prog: Prog): Data = evalProg(env, prog)

  private def evalProg(env: Env, prog: Prog): Data = {
    // TODO
    var retval: Data = Nil()
    for (stmt <- prog.stmts) retval = evalStmt(env, stmt)
    retval
  }

  private def evalVar(env: Env, va: Var): Data = env.get(va)

  private def evalBlock(env: Env, block: Block): Data = block match {
    case Block(defs, body) =>
      defs.map(d => evalDef(env, d))
      body.map(e => evalExpr(env, e)).last
    case _ => Nil()
  }

  private def evalBound(env: Env, bound: Bound): Data = {
    // TODO
    Nil()
  }

  private def evalStmt(env: Env, stmt: Stmt): Data = stmt match {
    case ComUnit(com) => evalExpr(env, com)
    case DefUnit(dfn) => evalDef(env, dfn)
    // case BeginCD
    case _ => Nil()
  }

  private def evalExpr(env: Env, expr: Expr): Data = expr match {
    case VarExpr(va) => evalVar(env, va)
    case Literal(lit) => evalLit(env, lit)
    case ProcCall(opr, opd) => evalExpr(env, opr) match {
      case Func(f) => f(opd.map(e => evalExpr(env, e)))
      case _ => Nil()
    }
    case LambExpr(args, body) =>
      val argFunc = args.map(x => evalVar(env, x))
      val bodyFunc = evalBlock(env, body)
      Clos(env, args, argFunc => bodyFunc)
    case CondExpr(test, con, alt) => evalExpr(env, test) match {
      case Bool(true) => evalExpr(env, con)
      case Bool(false) => alt match {
        case Some(e) => evalExpr(env, e)
        case None => Nil()
      }
    }
    case Assign(va, expr) => env.set(va, evalExpr(env, expr))
    // case Derive
    case _ => Nil()
  }

  private def evalDef(env: Env, dfn: Def): Data = dfn match {
    case VarDef(va, expr) => env.put(va, evalExpr(env, expr))
    case FuncDef(va, args, body) =>
      val argFunc = args.map(x => evalVar(env, x))
      val bodyFunc = evalBlock(env, body)
      val f = Func(argFunc => bodyFunc)
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
