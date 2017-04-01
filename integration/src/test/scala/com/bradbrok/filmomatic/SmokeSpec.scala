package com.bradbrok.filmomatic

import com.pi4j.io.gpio.{RaspiPin, PinState}
import org.scalatest.{FlatSpec, Matchers}
import com.bradbrok.filmomatic.hardware.GpioPins._

class SmokeSpec extends FlatSpec with Matchers {
  "Reading pin state" should "not fail" in {

    val pin = pin1

    pin.setState(PinState.LOW)

    pin.isLow should be(true)
  }

}
