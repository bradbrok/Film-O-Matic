package com.bradbrok.filmomatic

import akka.actor.Actor
import akka.actor.Actor.Receive
import com.bradbrok.filmomatic.JobActor.Start
import com.bradbrok.filmomatic.Message.Request

object JobActor {
  trait Start extends Request {
    def state: JobState
  }
}

class JobActor extends Actor {
  var state = JobState(1)

  override def receive: Receive = {
    case start: Start =>
  }
}
