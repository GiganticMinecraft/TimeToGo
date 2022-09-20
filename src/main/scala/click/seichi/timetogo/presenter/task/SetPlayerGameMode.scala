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
      modeTrigger <- useCase.enabledModeTrigger
    } yield GameModeChanger.change(player, modeTrigger.gameMode.asBukkit)
  }
}
