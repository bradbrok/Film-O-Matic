package com.bradbrok.filmomatic.state

import com.bradbrok.filmomatic.state.State._

import scala.concurrent.duration._
import scala.language.postfixOps

case class Step(state: State = Idle,
                duration: Duration = 0 seconds,
                delay: Duration = 0 seconds,
                temperature: Option[Double] = None)
