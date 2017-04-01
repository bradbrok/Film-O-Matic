package com.bradbrok.filmomatic

import com.pi4j.io.gpio.{GpioFactory, RaspiPin, PinState}
import org.scalatest.{FlatSpec, Matchers}
import com.bradbrok.filmomatic.hardware.ProtocolInstance.gpio

class SmokeSpec extends FlatSpec with Matchers {
  "Reading pin state" should "not fail" in {

    val pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Pin 2", PinState.HIGH)

    pin.setState(PinState.LOW)

    println(pin, pin.isLow)

    pin.isLow should be(true)
  }

}
