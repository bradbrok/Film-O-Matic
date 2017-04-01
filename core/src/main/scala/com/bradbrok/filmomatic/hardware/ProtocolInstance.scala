package com.bradbrok.filmomatic.hardware

import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.i2c.{I2CFactory, I2CBus}
import com.pi4j.io.w1.W1Master

/**
  * Created by bradbrok on 3/31/17.
  */
object ProtocolInstance {
  //GPIO instance, get once or it all burns down.
  lazy val gpio = GpioFactory.getInstance()
  //I2C instance, same as above. This only supports B+ models from Raspberry Pi 2 and above.
  lazy val i2c = I2CFactory.getInstance(I2CBus.BUS_1)
}
