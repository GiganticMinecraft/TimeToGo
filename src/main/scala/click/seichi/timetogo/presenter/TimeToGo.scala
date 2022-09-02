package click.seichi.timetogo.presenter

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TimeToGo extends JavaPlugin {
  override def onEnable(): Unit = {
    Bukkit.getServer.getLogger.info("TimeToGo is Enabled!")
  }
}
