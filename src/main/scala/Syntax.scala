package scalalisp

/*
 * object Op extends Enumeration {
 *   val Add, Sub, Or, And = Value
 * }
 */
sealed trait Op
case class Add() extends Op
case class Sub() extends Op
case class Or() extends Op
case class And() extends Op

sealed trait Expr
case class BoolLit(b: Boolean) extends Expr
case class IntLit(i: Int) extends Expr
case class BinOp(op: Op, x1: Expr, x2: Expr) extends Expr

sealed trait Val
case class UnitV() extends Val
case class BoolV(b: Boolean) extends Val
case class IntV(i: Int) extends Val
