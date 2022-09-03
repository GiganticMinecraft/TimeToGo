package click.seichi.timetogo.presenter

import org.bukkit.GameMode
import org.bukkit.entity.Player

object GameModeChanger {
  def isIgnoredPlayer(player: Player): Boolean = player.hasPermission("timetogo.ignore")

  def change(player: Player, gameMode: GameMode): Unit =
    if (!isIgnoredPlayer(player) && player.getGameMode != gameMode) player.setGameMode(gameMode)
}
