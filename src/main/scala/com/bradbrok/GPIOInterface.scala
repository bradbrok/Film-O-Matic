package com.bradbrok

import com.pi4j.io.gpio.{GpioFactory, GpioPin, RaspiPin};


/**
  * @author bradbrok on 1/25/17.
  */
class GPIOInterface {
  val gpio = GpioFactory.getInstance()
  val pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "pin1")
  val pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "pin2")
  val pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "pin3")
  val pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "pin4")
  val pin5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "pin5")
  val pin6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "pin6")
  val pin7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "pin7")
  val pin8 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "pin8")
  val pin9 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, "pin9")
  val pin10 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, "pin10")
  val pin11 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "pin11")
  val pin12 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, "pin12")
  val pin13 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, "pin13")
  val pin14 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14, "pin14")
  val pin15 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "pin15")
  val pin16 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, "pin16")

  def setHigh(pins: List[GpioPin]): Unit = {
    //Need to pass a list to set each pin high.
    for (pin <- pins) gpio.high()
  }

  def setLow(pins: List[GpioPin]): Unit = {
    for (pin <- pins) gpio.low()
  }
}
