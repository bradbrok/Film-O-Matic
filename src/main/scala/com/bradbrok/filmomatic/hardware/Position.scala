package com.bradbrok.filmomatic.hardware

/**
  * @author bradbrok on 3/17/17.
  */
object Position {
  case object A extends Position {
    val position = 0
  }
  case object B extends Position {
    val position = 1
  }
  case object C extends Position {
    val position = 3
  }
  case object D extends Position {
    val position = 2
  }
}

trait Position{
  def apply(input: Int): Position
}