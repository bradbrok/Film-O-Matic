package com.bradbrok.filmomatic.state

import com.bradbrok.filmomatic.state.State._
import org.scalatest._

import scala.concurrent.duration._
import scala.language.postfixOps

class PlanSpec extends FlatSpec with Matchers {

  "A balanced Plan" should "report itself as balanced" in {
    val plan1 = Plan(stages = List(
      Stage(bath = Some(Bath.A), steps = List(
        Step(Idle, duration = 5 seconds),
        Step(Fill, duration = 20 seconds),
        Step(Settle, duration = 5 seconds, temperature = Some(47)),
        Step(Agitate, duration = 5 seconds, temperature = Some(47)),
        Step(Settle, duration = 5 seconds, temperature = Some(47)),
        Step(Agitate, duration = 5 seconds, temperature = Some(47)),
        Step(Settle, duration = 5 seconds),
        Step(Reclaim, duration = 20 seconds),
        Step(Idle, duration = 5 seconds)
      ))
    ))
    plan1.isBalanced shouldBe true
  }

  "A plan with no steps" should "report that it has no steps" in {
    val plan2  = Plan(stages = List(
      Stage(bath = Some(Bath.A), steps = Nil)
    ))
    plan2.isBalanced shouldBe true
  }

  "A plan that is imbalanced" should "report that it's imbalanced" in {
    val plan3 = Plan(stages = List(
      Stage(bath = Some(Bath.A), steps = List(
        Step(Waste, duration = 5 seconds),
        Step(Agitate, duration = 5 seconds),
        Step(Idle, duration = 5 seconds)
      ))
    ))
    plan3.isBalanced shouldBe false
  }
}
