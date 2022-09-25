package click.seichi.timetogo.presenter.listener

import click.seichi.timetogo.presenter.GameModeChanger
import click.seichi.timetogo.presenter.TimeToGo.useCase
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.{EventHandler, Listener}

object PlayerLoginListener extends Listener {
  @EventHandler
  def onPlayerLogin(event: PlayerJoinEvent): Unit =
    for {
      modeTrigger <- useCase.findEnabled
      gameMode = modeTrigger.gameMode.asBukkit
      player = event.getPlayer
    } yield GameModeChanger.changeWithNotification(player, gameMode)
}
