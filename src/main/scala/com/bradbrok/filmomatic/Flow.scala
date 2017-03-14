package com.bradbrok.filmomatic

object Flow extends Enumeration {
  type Flow = Value
  val Idle, Fill, Settle, Agitate, Drain = Value
}