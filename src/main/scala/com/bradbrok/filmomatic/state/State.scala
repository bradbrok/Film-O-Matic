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
      * Determines if the next [[State]] is valid against
      * the current one.
      *
      * @example
      * {{{
      * assert(Idle ~> Fill)      // Pass
      * assert(Idle ~> Alternate) // Fail
      * }}}
      * @param next the subsequent value
      * @return true if the subsequent is allowable
      */
    def ~>(next: State): Boolean = canBecome contains next
  }
  case object Idle extends State {
    val direction = None
    val canBecome: Set[State] = Set(Idle, Fill)
  }
  case object Fill extends State {
    val direction = Some(In)
    val canBecome: Set[State] = Set(Settle, Agitate, Drain)
  }
  case object Settle extends State {
    val direction = None
    val canBecome: Set[State] = Set(Agitate, Drain)
  }
  case object Agitate extends State {
    val direction = Some(Alternate)
    val canBecome: Set[State] = Set(Settle, Drain)
  }
  case object Drain extends State {
    val direction = Some(Out)
    val canBecome: Set[State] = Set(Idle, Fill)
  }
}