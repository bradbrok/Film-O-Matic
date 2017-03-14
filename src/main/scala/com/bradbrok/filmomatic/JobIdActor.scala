package com.bradbrok.filmomatic

import akka.actor.Actor

object JobIdActor {
  import com.bradbrok.filmomatic.Message._

  type JobId = Long

  trait IncrementAndGet extends Request
  case class UniqueJobId(id: JobId, request: IncrementAndGet) extends Response
}

class JobIdActor extends Actor {
  import JobIdActor._

  var id: JobId = 0

  override def receive: Receive = {
    case request: IncrementAndGet =>
      id = id + 1
      sender ! UniqueId(id, request)
  }
}
