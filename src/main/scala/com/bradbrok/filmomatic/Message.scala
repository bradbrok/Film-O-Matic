package com.bradbrok.filmomatic

object Message {
  trait Request
  trait Response {
    def request: Request
  }
}
