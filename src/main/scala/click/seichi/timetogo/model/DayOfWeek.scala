package click.seichi.timetogo.model

import java.time.{DayOfWeek => JavaDayOfWeek}

sealed abstract class DayOfWeek(val asJava: JavaDayOfWeek) {
  def entryName: String = this.toString
}

object DayOfWeek {
  def values: Set[DayOfWeek] = Set(Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday)

  def fromString(str: String): Option[DayOfWeek] =
    values.find(_.entryName.toLowerCase == str.toLowerCase)

  case object Sunday extends DayOfWeek(JavaDayOfWeek.SUNDAY)

  case object Monday extends DayOfWeek(JavaDayOfWeek.MONDAY)

  case object Tuesday extends DayOfWeek(JavaDayOfWeek.TUESDAY)

  case object Wednesday extends DayOfWeek(JavaDayOfWeek.WEDNESDAY)

  case object Thursday extends DayOfWeek(JavaDayOfWeek.THURSDAY)

  case object Friday extends DayOfWeek(JavaDayOfWeek.FRIDAY)

  case object Saturday extends DayOfWeek(JavaDayOfWeek.SATURDAY)
}
