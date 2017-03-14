package com.bradbrok.filmomatic

object Flow {
  sealed trait Flow {
    /**
      * @return valid possible subsequent values
      */
    def ~>(): Set[Flow]
  }
  case object Idle extends Flow {
    val ~> = Set(Idle)
  }
  case object Fill extends Flow {
    val ~> = Set(Settle, Agitate, Drain)
  }
  case object Settle extends Flow {
    val ~> = Set(Agitate, Drain)
  }
  case object Agitate extends Flow {
    val ~> = Set(Settle, Drain)
  }
  case object Drain extends Flow {
    val ~> = Set(Idle, Fill)
  }
}