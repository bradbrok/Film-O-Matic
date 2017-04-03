package com.bradbrok.filmomatic

import com.pi4j.io.gpio.{PinState, RaspiPin}
import org.scalatest.{FlatSpec, Matchers}
import com.bradbrok.filmomatic.hardware.GpioPins._
import com.pi4j.io.gpio.event.{GpioPinDigitalStateChangeEvent, GpioPinListenerDigital}

class SmokeSpec extends FlatSpec with Matchers {
  "Reading pin state" should "not fail" in {

    for (pin <- listAllOutputPins) {
      pin.setState(PinState.LOW)
      Thread sleep 100
    }

    pin8.isLow should be(true)
  }

  "Reading rotary pin state" should "not fail" in {
    var a: Int = 0
    var b: Int = 0
    pinA.addListener(new GpioPinListenerDigital {
      override def handleGpioPinDigitalStateChangeEvent(event: GpioPinDigitalStateChangeEvent): Unit = {
        println("A", event.getEdge, event.getState)
        a += 1
      }
    })
    pinB.addListener(new GpioPinListenerDigital {
      override def handleGpioPinDigitalStateChangeEvent(event: GpioPinDigitalStateChangeEvent): Unit = {
        println("B", event.getEdge, event.getState)
        b += 1
      }
    })
    while (true){
      Thread sleep 1000
      println(a, b)
      if (a >= 1) a should be 1
    }
  }
}
