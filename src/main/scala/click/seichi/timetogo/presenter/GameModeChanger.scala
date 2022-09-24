package click.seichi.timetogo.presenter

import org.bukkit.{ChatColor, GameMode}
import org.bukkit.entity.Player

object GameModeChanger {
  private def isIgnoredPlayer(player: Player): Boolean = player.hasPermission("timetogo.ignore")

  private def shouldBeChanged(player: Player, gameMode: GameMode): Boolean =
    !isIgnoredPlayer(player) && player.getGameMode != gameMode

  def change(player: Player, gameMode: GameMode): Unit =
    if (shouldBeChanged(player, gameMode)) player.setGameMode(gameMode)

  def changeWithNotification(player: Player, gameMode: GameMode): Unit = {
    player.sendMessage(s"${ChatColor.AQUA}現在の時刻ではゲームモードが${gameMode}に変更されます。")
    change(player, gameMode)
  }
}
