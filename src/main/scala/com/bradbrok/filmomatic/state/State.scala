package com.bradbrok.filmomatic.state

import com.bradbrok.filmomatic.state.Direction._

object State {
  sealed trait State {
    /**
      * @return the desired pump [[Direction]]
      */
    def direction: Option[Direction]

    /**
      * @return valid possible subsequent [[State]] values
      */
    def canBecome: Set[State]

    /**
      * @example
      * {{{
      * assert(Idle ~> Fill)
      * }}}
      * @param next the subsequent value
      * @return true if the subsequent is allowable
      */
    def ~>(next: State): Boolean = canBecome contains next
  }
  case object Idle extends State {
    val direction = None
    val canBecome = Set(Idle, Fill)
  }
  case object Fill extends State {
    val direction = Some(In)
    val canBecome = Set(Settle, AgitateA, Drain)
  }
  case object Settle extends State {
    val direction = None
    val canBecome = Set(AgitateA, Drain)
  }
  case object AgitateA extends State {
    val direction = Some(Out)
    val canBecome = Set(AgitateB)
  }
  case object AgitateB extends State {
    val direction = Some(In)
    val canBecome = Set(Settle, Drain)
  }
  case object Drain extends State {
    val direction = Some(Out)
    val canBecome = Set(Idle, Fill)
  }
}