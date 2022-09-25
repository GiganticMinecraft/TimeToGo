package click.seichi.timetogo.infra

import java.time.DayOfWeek

object DayOfWeekHelper {
  def values: Set[DayOfWeek] = DayOfWeek.values().toSet

  def fromString(str: String): Option[DayOfWeek] =
    values.find(_.name.toLowerCase == str.toLowerCase)
}
