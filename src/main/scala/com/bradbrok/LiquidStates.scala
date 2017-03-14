/**
  * @author bradbrok on 3/13/17.
  */

object LiquidStates extends Enumeration {
  type LiquidStates = Value
  //The bath states are A, B, & C. "F" means fill, "R" means reverse into same tank, "A" means Agitate
  val BathAF, BathAR, BathAA, BathBF, BathBR, BathBA, BathCF, BathCR, BathCA, BathWater, BathWaste = Value
}