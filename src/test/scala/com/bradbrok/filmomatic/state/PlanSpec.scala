package com.bradbrok.filmomatic.state

import com.bradbrok.filmomatic.state.State._
import org.scalatest._

import scala.concurrent.duration._
import scala.language.postfixOps

class PlanSpec extends FlatSpec with Matchers {

  "A balanced Plan" should "report itself as balanced" in {
    val plan = Plan(stages = List(
      Stage(bath = Some(Bath.A), steps = List(
        Step(Idle, duration = 5 seconds),
        Step(Fill, duration = 20 seconds),
        Step(Settle, duration = 5 seconds, temperature = Some(47)),
        Step(Agitate, duration = 5 seconds, temperature = Some(47)),
        Step(Settle, duration = 5 seconds, temperature = Some(47)),
        Step(Agitate, duration = 5 seconds, temperature = Some(47)),
        Step(Settle, duration = 5 seconds),
        Step(Drain, duration = 20 seconds),
        Step(Idle, duration = 5 seconds)
      )),
      Stage(bath = Some(Bath.B), steps = List(
        Step(Fill, duration = 20 seconds),
        Step(Settle, duration = 30 seconds, temperature = Some(42)),
        Step(Agitate, duration = 10 seconds, temperature = Some(42)),
        Step(Waste, duration = 20 seconds)
      ))
    ))
    plan.isBalanced shouldBe true
  }

}
