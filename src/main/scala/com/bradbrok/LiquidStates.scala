package com.bradbrok

/**
  * @author bradbrok on 3/13/17.
  */
object LiquidStates extends Enumeration {
  type LiquidStates = Value
  //The bath states are A, B, & C. "F" means fill, "R" means reverse into same tank, "A" means Agitate
  val BathAFill, BathAReverse, BathAAgitate, BathAWaste,
    BathBFill, BathBReverse, BathBAgitate, BathBWaste,
    BathCFill, BathCReverse, BathCAgitate, BathCWaste,
    BathWaterFill, BathWaterWaste = Value
}