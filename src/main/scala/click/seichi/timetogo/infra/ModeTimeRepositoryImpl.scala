package click.seichi.timetogo.infra

import click.seichi.timetogo.model.{GameMode, ModeTime, ModeTimeRepository}
import org.bukkit.plugin.java.JavaPlugin

import java.time.LocalTime
import java.util
import scala.jdk.CollectionConverters._
import scala.util.Try

case class ModeTimeRepositoryImpl(instance: JavaPlugin) extends ModeTimeRepository {
  private def config = instance.getConfig

  override def list: List[ModeTime] = {
    for {
      map <- config.getList("mode-time").asScala.toList.asInstanceOf[List[util.LinkedHashMap[String, String]]]
      gameMode <- GameMode.fromString(map.get("game-mode"))
      time <- Try(LocalTime.parse(map.get("time"))).toOption
    } yield ModeTime(gameMode, time)
  }
}
