package click.seichi.timetogo.presenter.task

import click.seichi.timetogo.presenter.GameModeChanger
import click.seichi.timetogo.presenter.TimeToGo.useCase
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

import scala.jdk.CollectionConverters._

object SetPlayerGameMode extends BukkitRunnable {
  override def run(): Unit = {
    for {
      player <- Bukkit.getOnlinePlayers.asScala
      modeTime <- useCase.enabledModeTime
    } yield GameModeChanger.change(player, modeTime.gameMode.asBukkit)
  }
}
