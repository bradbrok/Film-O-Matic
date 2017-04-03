package com.bradbrok.filmomatic.hardware

import com.bradbrok.filmomatic.hardware.ProtocolInstance.i2c
import java.awt._
import java.awt.image.BufferedImage

import com.pi4j.io.i2c._
import eu.ondryaso.ssd1306.Display

/**
  * @author bradbrok on 3/20/17.
  */

case class Oled(shouldWork: Boolean) {
  val device = i2c.getDevice(0x3c)
  //Address space is usually 0x3C
  val oled = new Display(128, 64, null, i2c, 0x3c)
  oled.begin()
  val imageSize = (128, 64)
  val canvas = new BufferedImage(imageSize._1, imageSize._2, BufferedImage.TYPE_INT_RGB)
  val getGraphics = canvas.createGraphics()
  //This should fill the entire screen, black means light pixel up.
  getGraphics.setColor(Color.BLACK)
  getGraphics.fillRect(0, 0, canvas.getWidth, canvas.getHeight)
  oled.displayImage()
  oled.display()
  Thread.sleep(1000)
  getGraphics.setColor(Color.WHITE)
  getGraphics.fillRect(0, 0, canvas.getWidth, canvas.getHeight)
  getGraphics.drawString("FILM-O-MATIC", 0, 20)
  oled.displayImage()
  oled.display()
}