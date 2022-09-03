package click.seichi.timetogo.presenter.listener

import click.seichi.timetogo.presenter.TimeToGo.useCase
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.{EventHandler, Listener}

object PlayerLoginListener extends Listener {
  @EventHandler
  def onPlayerLogin(event: PlayerJoinEvent): Unit =
    useCase.enabledModeTime.map(_.gameMode.asBukkit).foreach(event.getPlayer.setGameMode)
}
