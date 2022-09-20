package click.seichi.timetogo.usecase.`trait`

import java.time.{DayOfWeek, LocalTime}

trait Clock {
  def now_time: LocalTime

  def now_day_of_week: DayOfWeek
}
