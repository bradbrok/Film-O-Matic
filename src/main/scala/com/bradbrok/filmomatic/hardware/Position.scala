package com.bradbrok.filmomatic.hardware

/**
  * @author bradbrok on 3/17/17.
  */
object Positions {

  sealed trait Position{
  }

  case object A extends Position {
    val input = 0
    val leftOf = D
    val rightOf = B
  }
  case object B extends Position {
    val input = 1
    val leftOf = A
    val rightOf = C
  }
  case object C extends Position {
    val input = 3
    val leftOf = B
    val rightOf = D
  }
  case object D extends Position {
    val input = 2
    val leftOf = C
    val rightOf = A
  }
}

