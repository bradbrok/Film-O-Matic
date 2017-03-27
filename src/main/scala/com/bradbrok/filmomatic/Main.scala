package com.bradbrok.filmomatic

import com.bradbrok.filmomatic.{GPIOInterface => Gpio}
import scala.concurrent.duration._

/**
  * @author bradbrok on 1/23/17.
  */

object Main extends App {
  override def main(args: Array[String]): Unit = {
      Gpio(true)
    }
  }


