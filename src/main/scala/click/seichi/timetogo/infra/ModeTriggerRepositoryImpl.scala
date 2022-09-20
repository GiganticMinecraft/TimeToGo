package click.seichi.timetogo.infra

import click.seichi.timetogo.model.{GameMode, ModeTrigger, ModeTriggerRepository}
import org.bukkit.plugin.java.JavaPlugin

import java.time.{DayOfWeek, LocalTime}
import java.util
import scala.jdk.CollectionConverters._
import scala.util.Try

case class ModeTriggerRepositoryImpl(instance: JavaPlugin) extends ModeTriggerRepository {
  private def config = instance.getConfig

  override def list: List[ModeTrigger] = {
    for {
      map <- config
        .getList("mode-time")
        .asScala
        .toList
        .asInstanceOf[List[util.LinkedHashMap[String, _]]]
      gameMode <- GameMode.fromString(map.get("game-mode").asInstanceOf[String])
      daysOfWeek = map
        .get("days-of-week")
        .asInstanceOf[util.List[String]]
        .asScala
        .map(str => Try(DayOfWeek.valueOf(str)).toOption)
        .withFilter(_.isDefined)
        .map(_.get)
        .toSet
      time <- Try(LocalTime.parse(map.get("time").asInstanceOf[String])).toOption
    } yield {
      val set = if (daysOfWeek.isEmpty) DayOfWeek.values().toSet else daysOfWeek

      ModeTrigger(gameMode, set, time)
    }
  }
}
