package com.bradbrok.filmomatic.state

import com.bradbrok.filmomatic.state.Bath.Bath

case class Stage(bath: Bath, steps: List[Step] = Nil)