package click.seichi.timetogo.infra

import java.time.LocalTime
import scala.util.Try

object LocalTimeHelper {
  def parse(str: String): Option[LocalTime] = Try(LocalTime.parse(str)).toOption
}
