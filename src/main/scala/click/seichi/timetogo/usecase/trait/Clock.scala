package click.seichi.timetogo.usecase.`trait`

import java.time.{DayOfWeek, LocalTime}

trait Clock {
  def time: LocalTime

  def dayOfWeek: DayOfWeek
}
