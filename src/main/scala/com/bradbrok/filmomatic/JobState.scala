package com.bradbrok.filmomatic

import com.bradbrok.filmomatic.Bath._
import com.bradbrok.filmomatic.State._
import com.bradbrok.filmomatic.JobIdActor.JobId

import scala.concurrent.duration._
import scala.language.postfixOps

case class JobState(id: JobId,
                    bath: Bath = Unselected,
                    flow: State = Idle,
                    duration: Duration = 1 second)