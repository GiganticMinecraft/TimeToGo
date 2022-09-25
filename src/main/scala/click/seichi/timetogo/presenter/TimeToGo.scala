package click.seichi.timetogo.presenter

import click.seichi.timetogo.infra.ModeTriggerRepositoryImpl
import click.seichi.timetogo.model.ModeTriggerRepository
import click.seichi.timetogo.presenter.command.CommandHandler
import click.seichi.timetogo.presenter.listener.PlayerLoginListener
import click.seichi.timetogo.presenter.task.SetPlayerGameMode
import click.seichi.timetogo.usecase.ModeTriggerUseCase
import click.seichi.timetogo.usecase.`trait`.Clock
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

import java.time.{DayOfWeek, LocalDate, LocalTime}

class TimeToGo extends JavaPlugin {
  private var task: BukkitTask = _

  TimeToGo.instance = this

  override def onEnable(): Unit = {
    saveDefaultConfig()
    reloadConfig()

    Bukkit.getPluginManager.registerEvents(PlayerLoginListener, this)
    Bukkit.getPluginCommand("timetogo").setExecutor(CommandHandler)

    this.task = SetPlayerGameMode.runTaskTimer(this, 0L, 20 * 60L)

    getLogger.info("TimeToGo is Enabled!")
  }

  override def onDisable(): Unit = {
    this.task.cancel()

    getLogger.info("TimeToGo is Disabled!")
  }
}

object TimeToGo {
  var instance: TimeToGo = _

  lazy val useCase: ModeTriggerUseCase = new ModeTriggerUseCase {
    val clock: Clock = new Clock {
      def now_time: LocalTime = LocalTime.now
      def now_day_of_week: DayOfWeek = LocalDate.now.getDayOfWeek
    }

    // functionでないと、`config.yml`に変更があっても読み込まれない
    def repository: ModeTriggerRepository = ModeTriggerRepositoryImpl(instance.getConfig)
  }
}
