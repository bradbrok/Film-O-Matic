package com.bradbrok.filmomatic

import akka.actor.Actor
import com.bradbrok.filmomatic.JobActor.Start
import com.bradbrok.filmomatic.Message.Request
import com.bradbrok.filmomatic.state.Plan

object JobActor {
  trait Start extends Request {
    def plan: Plan
  }
}

class JobActor extends Actor {
  var plan: Plan = _

  override def receive: Receive = {
    case start: Start =>
      plan = start.plan
  }
}
