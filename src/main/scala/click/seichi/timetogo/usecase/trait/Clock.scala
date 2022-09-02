package click.seichi.timetogo.usecase.`trait`

import java.time.LocalTime

trait Clock {
  def now: LocalTime
}
