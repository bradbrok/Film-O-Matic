package com.bradbrok.filmomatic

import com.bradbrok.filmomatic.State.State

import scala.concurrent.duration.Duration

case class Step(stage: State, duration: Duration)
