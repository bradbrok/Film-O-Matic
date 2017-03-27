package com.bradbrok.filmomatic

import com.pi4j.io.w1.W1Master
import com.pi4j.component.temperature.TemperatureSensor
import com.pi4j.temperature.TemperatureScale
import com.pi4j.io.gpio.{PinState, GpioFactory, GpioPinDigitalOutput, RaspiPin, PinPullResistance}
;


/**
  * @author bradbrok on 1/25/17.
  */
case class GPIOInterface(shouldWork: Boolean){
  //Share this initialization of GpioFactory across rest of project.
  val gpio = GpioFactory.getInstance()
  //GPIO for relay
  //OUTPUTS
  val pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "pin1", PinState.LOW)
  val pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "pin2", PinState.LOW)
  val pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "pin3", PinState.LOW)
  val pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "pin4", PinState.LOW)
  val pin5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_21, "pin5", PinState.LOW)
  val pin6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22, "pin6", PinState.LOW)
  val pin7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "pin7", PinState.LOW)
  val pin8 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24, "pin8", PinState.LOW)
  //INPUTS
  val pinA = gpio.provisionDigitalInputPin(RaspiPin.GPIO_13, "pinA", PinPullResistance.PULL_DOWN)
  val pinB = gpio.provisionDigitalInputPin(RaspiPin.GPIO_14, "pinB", PinPullResistance.PULL_DOWN)
  val pinClick = gpio.provisionDigitalInputPin(RaspiPin.GPIO_15, "clickPin", PinPullResistance.PULL_DOWN)
  //W1 for temp probe
  val w1Master = new W1Master()
  println(w1Master)


  val pins = List(pin1, pin2, pin3, pin4, pin5, pin6, pin7, pin8)

  def setHigh(pins: List[GpioPinDigitalOutput]): Unit = {
    pins.foreach(_.high)
  }

  def setLow(pins: List[GpioPinDigitalOutput]): Unit = {
    pins.foreach(_.low)
  }

}
