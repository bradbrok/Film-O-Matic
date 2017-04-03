package com.bradbrok.filmomatic.hardware

import akka.actor.Actor
import com.pi4j.io.gpio.PinState

/**
  * Created by bradbrok on 4/1/17.
  */
class PinActor extends Actor {
  def receive: Receive = {
    case setHigh => PinState.HIGH
    case setLow => PinState.LOW
  }
}
