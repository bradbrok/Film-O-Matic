package com.bradbrok.filmomatic.hardware

/**
  * @author bradbrok on 3/16/17.
  */

case class RotaryEncoder(pinA: Int, pinB: Int) {

  /** Decode the current state and return Int value.
    *
    * @param pinA is either 1 or 0
    * @param pinB is either 1 or 0
    * @return bitwsie conversion of input to 0..3
    */
  def decodeCurrentState = (pinA << 1) | pinB
}

object RotaryDirection {

}



