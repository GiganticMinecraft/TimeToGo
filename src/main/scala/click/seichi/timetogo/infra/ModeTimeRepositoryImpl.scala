package click.seichi.timetogo.infra

import click.seichi.timetogo.model.{ModeTime, ModeTimeRepository}
import org.bukkit.plugin.java.JavaPlugin

import scala.jdk.CollectionConverters._

case class ModeTimeRepositoryImpl(instance: JavaPlugin) extends ModeTimeRepository {
  private def config = instance.getConfig

  override def list(): List[ModeTime] = config.getList("mode-time").asScala.toList.map(_.asInstanceOf[ModeTime])
}
