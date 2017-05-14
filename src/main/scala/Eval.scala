package scalalisp

object Eval {
  def apply(prog: Prog): Data = evalProg(prog)

  private def evalProg(prog: Prog): Data = {
    // TODO
    var a: Data = Nil()
    for (stmt <- prog.stmts) a = evalStmt(stmt)
    a
  }

  private def evalStmt(stmt: Stmt): Data = stmt match {
    // case VarDef
    // case FuncDef
    // case BeginDef
    case _ => Nil()
  }

  private def evalExpr(expr: Expr): Data = expr match {
    // case VarExpr
    case Literal(lit) => evalLit(lit)
    // case Proc
    // case CondExpr
    // case Assign
    // case Derive
    case _ => Nil()
  }

  private def evalDef(dfn: Def): Data = dfn match {
    // case VarDef
    // case FuncDef
    // case BeginDef
    case _ => Nil()
  }

  private def evalDrv(drv: Drv): Data = drv match {
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

  private def evalLit(lit: Lit): Data = lit match {
    // case QuotLit(data)
    case BoolLit(b) => b
    case NumLit(n) => n
    case CharLit(c) => c
    case StrLit(s) => s
    case _ => Nil()
  }
}
