package com.bradbrok.filmomatic.state

case class Plan(stages: List[Stage] = Nil) {
  def isBalanced: Boolean = stages.forall(_.isBalanced)
}