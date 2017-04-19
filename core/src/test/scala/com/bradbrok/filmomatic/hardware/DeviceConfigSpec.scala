package com.bradbrok.filmomatic.hardware

import com.typesafe.config.ConfigFactory
import org.scalatest.{FlatSpec, Matchers}

class DeviceConfigSpec extends FlatSpec with Matchers {

  "Pin assignments" should "be parsable from configuration" in {
    val bindings = Device.bindings(ConfigFactory.load())
    bindings.size should be > 0
  }

}
