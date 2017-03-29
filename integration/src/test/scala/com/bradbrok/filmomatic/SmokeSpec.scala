package com.bradbrok.filmomatic

import com.pi4j.io.gpio.{GpioFactory, RaspiPin}
import org.scalatest.{FlatSpec, Matchers}

class SmokeSpec extends FlatSpec with Matchers {
  "Reading pin state" should "not fail" in {
    val gpio = GpioFactory.getInstance()

    val pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, "My Input Pin")

    pin.getState.isLow should be(true)
  }
}
