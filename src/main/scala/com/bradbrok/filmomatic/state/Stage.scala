package com.bradbrok.filmomatic.state

import com.bradbrok.filmomatic.state.Bath.Bath
import com.bradbrok.filmomatic.state.Direction._

import scala.concurrent.duration.Duration

case class Stage(bath: Option[Bath] = None, steps: List[Step] = Nil) {
  def durationsInDirection: Map[Direction, Duration] = steps.flatMap { step =>
    step.state.direction.map { direction =>
      (direction, step.duration)
    }
  }.groupBy(_._1).mapValues(_.map(_._2).reduceLeft((a, c) => a + c))

  def isBalanced: Boolean = durationsInDirection.filter({
    case (direction, _) => direction != Alternate
  }).values.toSet.size == 1
}