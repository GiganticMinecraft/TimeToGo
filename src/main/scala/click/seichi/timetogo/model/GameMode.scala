package click.seichi.timetogo.model

import org.bukkit.{GameMode => BukkitGameMode}

sealed abstract class GameMode(val asBukkit: BukkitGameMode) {
  def entryName: String = this.toString
}

object GameMode {
  def values: Set[GameMode] = Set(Survive, Creative, Adventure, Spectator)

  def fromString(str: String): Option[GameMode] =
    values.find(_.entryName.toLowerCase == str.toLowerCase)

  case object Survive extends GameMode(BukkitGameMode.SURVIVAL)

  case object Creative extends GameMode(BukkitGameMode.CREATIVE)

  case object Adventure extends GameMode(BukkitGameMode.ADVENTURE)

  case object Spectator extends GameMode(BukkitGameMode.SPECTATOR)
}
