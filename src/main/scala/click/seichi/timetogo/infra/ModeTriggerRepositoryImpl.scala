package click.seichi.timetogo.infra

import click.seichi.timetogo.model.{GameMode, ModeTrigger, ModeTriggerRepository}
import org.bukkit.configuration.file.FileConfiguration

import java.time.DayOfWeek
import java.util
import java.util.Collections.emptyList
import scala.jdk.CollectionConverters._

case class ModeTriggerRepositoryImpl(config: FileConfiguration) extends ModeTriggerRepository {
  override def list: List[ModeTrigger] = {
    def mapListAsDaysOfWeek(daysOfWeek: util.List[String]): Set[DayOfWeek] = {
      for {
        day <- daysOfWeek.asScala
        day <- DayOfWeekHelper.fromString(day)
      } yield day
    }.toSet

    for {
      map <- config
        .getList("mode-triggers")
        .asScala
        .toList
        .asInstanceOf[List[util.LinkedHashMap[String, _]]]
      gameMode <- Option(map.get("game-mode"))
      gameMode <- GameMode.fromString(gameMode.asInstanceOf[String])
      time <- Option(map.get("time"))
      time <- LocalTimeHelper.parse(time.asInstanceOf[String])
      daysOfWeekList = Option(map.get("days-of-week"))
        .getOrElse(emptyList)
        .asInstanceOf[util.List[String]]
      daysOfWeek = mapListAsDaysOfWeek(daysOfWeekList)
    } yield {
      val set = if (daysOfWeek.isEmpty) DayOfWeekHelper.values else daysOfWeek

      ModeTrigger(gameMode, set, time)
    }
  }
}
