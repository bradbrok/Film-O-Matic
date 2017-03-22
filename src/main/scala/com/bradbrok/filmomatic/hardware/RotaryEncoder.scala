package com.bradbrok.filmomatic.hardware

import com.bradbrok.filmomatic.hardware.Position._

/**
  * @author bradbrok on 3/16/17.
  */

case class RotaryEncoder(pinA: Int, pinB: Int) {
  var rotationDelta: Int = 0
  var currentRotationValue: Int = 0
  var previousRotationValue: Int = 0
  var steps: Int = 0

  def apply(pinA: Int, pinB: Int)

  /** Decode the current state and return Int value.
    *
    * @param pinA is either 1 or 0
    * @param pinB is either 1 or 0
    * @return bitwsie conversion of input to 0..3
    * 00 = 0
    * 01 = 1
    * 10 = 2
    * 11 = 3
    */
  def decodeCurrentState = (pinA << 1) | pinB

  def initRotationState = previousRotationValue = decodeCurrentState

  //Needs a callback for when either value changes. A|B
  def rotationDirectionUpdate(callback: Any)= {
    currentRotationValue = decodeCurrentState
    rotationDelta = 0
    if(currentRotationValue != previousRotationValue){
      rotationDelta = (previousRotationValue - currentRotationValue) % 4
      if(rotationDelta == 3){
        steps += 1
      }
      else if(rotationDelta == -3){
        steps -= 1
      }
      else{
        steps -= rotationDelta
      }
    }
    previousRotationValue = currentRotationValue
  }
}




