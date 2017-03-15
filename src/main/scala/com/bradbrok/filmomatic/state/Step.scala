package com.bradbrok.filmomatic.state

import com.bradbrok.filmomatic.state.State._

import scala.concurrent.duration.Duration

case class Step(stage: State, duration: Duration, delay: Duration)
