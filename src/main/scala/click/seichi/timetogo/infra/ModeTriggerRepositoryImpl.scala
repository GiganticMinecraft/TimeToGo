package click.seichi.timetogo.infra

import click.seichi.timetogo.model.{GameMode, ModeTrigger, ModeTriggerRepository}
import org.bukkit.plugin.java.JavaPlugin

import java.time.{LocalTime, DayOfWeek}
import java.util
import java.util.Collections.emptyList
import scala.jdk.CollectionConverters._
import scala.util.Try

case class ModeTriggerRepositoryImpl(instance: JavaPlugin) extends ModeTriggerRepository {
  private def config = instance.getConfig

  override def list: List[ModeTrigger] = {
    def mapListAsDaysOfWeek(daysOfWeek: util.List[String]): Set[DayOfWeek] = {
      for {
        day <- daysOfWeek.asScala
        day <- Try(DayOfWeekHelper.fromString(day)).toOption
        if day.isDefined
      } yield day.get
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
      time <- Try(LocalTime.parse(time.asInstanceOf[String])).toOption
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
