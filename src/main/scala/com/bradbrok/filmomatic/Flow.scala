package com.bradbrok.filmomatic

object Flow {
  sealed trait Flow {
    /**
      * @return valid possible subsequent values
      */
    def canBecome: Set[Flow]

    /**
      * @example
      * {{{
      * assert(Idle ~> Fill)
      * }}}
      * @param flow the subsequent value
      * @return true if the subsequent is allowable
      */
    def ~>(flow: Flow): Boolean = canBecome contains flow
  }
  case object Idle extends Flow {
    val canBecome = Set(Idle, Fill)
  }
  case object Fill extends Flow {
    val canBecome = Set(Settle, Agitate, Drain)
  }
  case object Settle extends Flow {
    val canBecome = Set(Agitate, Drain)
  }
  case object Agitate extends Flow {
    val canBecome = Set(Settle, Drain)
  }
  case object Drain extends Flow {
    val canBecome = Set(Idle, Fill)
  }
}