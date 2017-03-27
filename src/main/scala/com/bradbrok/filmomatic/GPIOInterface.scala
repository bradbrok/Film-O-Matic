package com.bradbrok.filmomatic

import com.pi4j.io.w1.W1Master
import com.pi4j.component.temperature.TemperatureSensor
import com.pi4j.temperature.TemperatureScale
import com.pi4j.io.gpio.{GpioFactory, RaspiGpioProvider, RaspiPinNumberingScheme, RaspiBcmPin, PinState, PinPullResistance
                        , GpioPinDigitalOutput}
;


/**
  * @author bradbrok on 1/25/17.
  */
case class GPIOInterface(shouldWork: Boolean){
  //Share this initialization of GpioFactory across rest of project.
  val gpio = GpioFactory.getInstance()
  GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING))
  println(gpio)
  //GPIO for relay
  //OUTPUTS
  val pin1 = gpio.provisionDigitalOutputPin(RaspiBcmPin.GPIO_04, "pin1", PinState.LOW)
  val pin2 = gpio.provisionDigitalOutputPin(RaspiBcmPin.GPIO_17, "pin2", PinState.LOW)
  //INPUTS
  val pinA = gpio.provisionDigitalInputPin(RaspiBcmPin.GPIO_24, "pinA", PinPullResistance.PULL_DOWN)
  val pinB = gpio.provisionDigitalInputPin(RaspiBcmPin.GPIO_23, "pinB", PinPullResistance.PULL_DOWN)
  val pinClick = gpio.provisionDigitalInputPin(RaspiBcmPin.GPIO_25, "clickPin", PinPullResistance.PULL_DOWN)
  //W1 for temp probe



  val pins = List(pin1, pin2)

  def setHigh(pins: List[GpioPinDigitalOutput]): Unit = {
    pins.foreach(_.high)
  }

  def setLow(pins: List[GpioPinDigitalOutput]): Unit = {
    pins.foreach(_.low)
  }

}
