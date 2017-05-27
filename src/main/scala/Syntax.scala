package scalalisp

trait ScmObject
trait ScmSyntax

/*********************************************************************
 * Object Definition
 *********************************************************************/
sealed trait Data extends ScmObject
case class Nil() extends Data
case class Bool(b: Boolean) extends Data
case class Num(i: Int) extends Data
case class Chr(c: Char) extends Data
case class Str(s: String) extends Data
case class Sym(s: String) extends Data
case class Lst(l: List[Data]) extends Data
case class DotLst(car: List[Data], cdr: Data) extends Data
case class AbbrLst(d: Data) extends Data
case class Vec(v: Array[Data]) extends Data
// adding Func to Data trait is extension from R5RS
case class Func(f: List[Data] => Data) extends Data
case class Clos(env: Env, args: List[Var], body: List[Data] => Data) extends Data

/*********************************************************************
 * Syntax Definition
 *********************************************************************/
case class Prog(stmts: List[Stmt]) extends ScmSyntax

case class Var(id: String) extends ScmSyntax
case class Block(defs: List[Def], body: List[Expr]) extends ScmSyntax
case class Bound(va: Var, expr: Expr) extends ScmSyntax

sealed trait Stmt extends ScmSyntax
case class ComUnit(com: Expr) extends Stmt
case class DefUnit(dfn: Def) extends Stmt
// case class SynDef() extends Stmt
case class BeginCD(stmts: List[Stmt]) extends Stmt

sealed trait Def extends ScmSyntax
case class VarDef(va: Var, expr: Expr) extends Def
case class FuncDef(va: Var, args: List[Var], body: Block) extends Def
case class BeginDef(defs: List[Def]) extends Def

sealed trait Expr extends ScmSyntax
case class VarExpr(va: Var) extends Expr
case class Literal(lit: Lit) extends Expr
case class ProcCall(opr: Expr, opd: List[Expr]) extends Expr
case class LambExpr(args: List[Var], body: Block) extends Expr
case class CondExpr(test: Expr, con: Expr, alt: Option[Expr]) extends Expr
case class Assign(va: Var, expr: Expr) extends Expr
case class Derive(drv: Drv) extends Expr
// case class MacroUse() extends Expr
// case class MacroBlock() extends Expr

sealed trait Drv extends ScmSyntax
// case class CondDrv() extends Drv
// case class ConsEls(, els: List[Expr]) extends Drv
// case class CaseDrv() extends Drv
// case class CaseEls() extends Drv
case class AndDrv(test: List[Expr]) extends Drv
case class OrDrv(test: List[Expr]) extends Drv
case class LetDrv(bnds: List[Bound], body: Block) extends Drv
case class LetVDrv(va: Var, bnds: List[Bound], body: Block) extends Drv
case class LetSDrv(bnds: List[Bound], body: Block) extends Drv
case class LetRDrv(bnds: List[Bound], body: Block) extends Drv
case class BeginDrv(body: List[Expr]) extends Drv
// case class DoDrv() extends Drv
case class DelayDrv(expr: Expr) extends Drv
// case class QQDrv() extends Drv

sealed trait Lit extends ScmSyntax
case class QuoteLit(d: String) extends Lit
case class BoolLit(b: Boolean) extends Lit
case class NumLit(n: Int) extends Lit
case class CharLit(c: Char) extends Lit
case class StrLit(s: String) extends Lit

sealed trait Key extends ScmSyntax
case class Quote() extends Key
case class Lambda() extends Key
case class If() extends Key
case class SetEx() extends Key
case class Begin() extends Key
case class Cond() extends Key
case class And() extends Key
case class Or() extends Key
case class Case() extends Key
case class Let() extends Key
case class LetStar() extends Key
case class LetRec() extends Key
case class Do() extends Key
case class Delay() extends Key
case class QuasiQuote() extends Key

