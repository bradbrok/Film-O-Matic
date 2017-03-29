package com.bradbrok.filmomatic.hardware

import com.bradbrok.filmomatic.hardware.Rotation._

/**
  * @author bradbrok on 3/17/17.
  */
object Position {
  def apply(input: Int): Position = input match {
    case 0 => A
    case 1 => B
    case 2 => D
    case 3 => C
  }
  sealed trait Position {
    def leftOf: Position
    def rightOf: Position
    def <(position: Position): Boolean = position == leftOf
    def >(position: Position): Boolean = position == rightOf
  }
  case object A extends Position {
    val leftOf = D
    val rightOf = B
  }
  case object B extends Position {
    val leftOf = A
    val rightOf = C
  }
  case object C extends Position {
    val leftOf = B
    val rightOf = D
  }
  case object D extends Position {
    val leftOf = C
    val rightOf = A
  }
}

