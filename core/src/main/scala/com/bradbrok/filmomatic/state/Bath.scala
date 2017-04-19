package com.bradbrok.filmomatic.state

sealed trait Bath

/**
  * Wraps the individual [[Bath]] subtypes so they
  * have short names. They can be accessed using
  * [[Bath.A]], [[Bath.B]], etc.
  */
object Bath {
  case object A extends Bath
  case object B extends Bath
  case object C extends Bath
  case object Water extends Bath
}