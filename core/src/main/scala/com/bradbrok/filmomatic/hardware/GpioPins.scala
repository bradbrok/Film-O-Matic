package com.bradbrok.filmomatic.hardware

import com.pi4j.io.gpio._
import com.bradbrok.filmomatic.hardware.ProtocolInstance.gpio

/**
  * Created by bradbrok on 3/31/17.
  */
object GpioPins {
  //Output pins, this should be configurable from UI.
  val pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "Pin 1", PinState.HIGH)
  val pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Pin 2", PinState.HIGH)
  val pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Pin 3", PinState.HIGH)
  val pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Pin 4", PinState.HIGH)
  val pin5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_21, "Pin 5", PinState.HIGH)
  val pin6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22, "Pin 6", PinState.HIGH)
  val pin7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "Pin 7", PinState.HIGH)
  val pin8 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24, "Pin 8", PinState.HIGH)
  //Should be convenient to make a list of all of these.
  val listAllOutputPins = List(pin1,pin2,pin3,pin4,pin5,pin6,pin7,pin8)
  //Set shutdown options for each.
  for (pin <- listAllOutputPins) pin.setShutdownOptions(true, PinState.HIGH)
  //Input pins, for rotary encoder. Resistance set to pull down to prevent pin state from floating.
  val pinA = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04, "Pin A", PinPullResistance.PULL_UP)
  val pinB = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05, "Pin B", PinPullResistance.PULL_UP)
  val pinClick = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06, "Pin Click", PinPullResistance.PULL_UP)

  val listAllInputPins = List(pinA, pinB, pinClick)

  for (pin <- listAllInputPins) pin.setShutdownOptions(true)
}