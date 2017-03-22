package com.bradbrok.filmomatic.hardware
import java.awt.image.BufferedImage
import eu.ondryaso.ssd1306.Display
import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.i2c.I2CFactory
import com.pi4j.io.gpio.RaspiPin
import java.awt._

/**
  * @author bradbrok on 3/20/17.
  */

case class Oled(shouldWork: Boolean){
  //Need to look at the address of the I2C device attached.
  val oled = new Display(128, 64, GpioFactory.getInstance(), I2CFactory.getInstance(1), 1, RaspiPin.GPIO_04)
  oled.begin()
  val imageSize = (128, 64)
  val canvas = new BufferedImage(imageSize._1, imageSize._2, BufferedImage.TYPE_INT_RGB)
  val getGraphics = canvas.createGraphics()
  //This should fill the entire screen, black means light pixel up.
  getGraphics.setColor(Color.BLACK)
  getGraphics.fillRect(0 , 0, canvas.getWidth, canvas.getHeight)
  getGraphics.drawString("FILM-O-MATIC", 0, 20)
  oled.displayImage()
  oled.display()
}