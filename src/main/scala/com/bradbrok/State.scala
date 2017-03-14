package com.bradbrok

object Bath extends Enumeration {
  type Bath = Value
  val A, B, C = Value
}

object Flow extends Enumeration {
  type Flow = Value
  val Fill, Drain, Agitate = Value
}
