package click.seichi.timetogo.presenter.task

import click.seichi.timetogo.presenter.TimeToGo.useCase
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

import scala.jdk.CollectionConverters._

object SetPlayerGameMode extends BukkitRunnable {
  override def run(): Unit = {
    for {
      modeTime <- useCase.enabledModeTime
      gameMode = modeTime.gameMode.asBukkit
      targettedPlayers = Bukkit.getOnlinePlayers.asScala.filter(_.getGameMode != gameMode)
    } yield targettedPlayers.foreach(_.setGameMode(gameMode))
  }
}
