package com.bradbrok.filmomatic.hardware

import com.typesafe.config.Config

sealed trait Device {
  def name: String
  def path: Seq[String] = List("device")
}

object Device {
  sealed trait Pump extends Device {
    override def path: Seq[String] = super.path :+ "pump"
  }
  sealed trait Valve extends Device {
    override def path: Seq[String] = super.path :+ "valve"
  }

  sealed trait State

  object Pump {
    // Pumps
    case object Fill extends Pump {
      override val name = "Fill Pump"
      override val path: Seq[String] = super.path :+ "fill"
    }
    case object Drain extends Pump {
      override val name = "Drain Pump"
      override val path: Seq[String] = super.path :+ "drain"
    }

    sealed trait State extends Device.State

    // States
    object State {
      case object On extends State
      case object Off extends State
    }
  }

  object Valve {
    // Valves
    case object A extends Valve {
      override val name = "Valve A"
      override val path: Seq[String] = super.path :+ "a"
    }
    case object B extends Valve {
      override val name = "Valve B"
      override val path: Seq[String] = super.path :+ "b"
    }
    case object C extends Valve {
      override val name = "Valve C"
      override val path: Seq[String] = super.path :+ "c"
    }
    case object Water extends Valve {
      override val name = "Water Valve"
      override val path: Seq[String] = super.path :+ "water"
    }
    case object Drain extends Valve {
      override val name = "Drain Valve"
      override val path: Seq[String] = super.path :+ "drain"
    }

    sealed trait State extends Device.State

    // States
    object State {
      case object Open extends State
      case object Closed extends State
    }
  }

  case class Binding(device: Device, output: String)

  def list: Seq[Device] = Seq(Pump.Fill, Pump.Drain,
    Valve.A, Valve.B, Valve.C, Valve.Water, Valve.Drain)

  def bindings(config: Config): Seq[Binding] = list.map { device =>
    Binding(device, config.getString(device.path.mkString(".")))
  }
}