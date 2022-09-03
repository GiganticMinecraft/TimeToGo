package click.seichi.timetogo.presenter

import click.seichi.timetogo.infra.ModeTimeRepositoryImpl
import click.seichi.timetogo.model.ModeTimeRepository
import click.seichi.timetogo.usecase.ModeTimeUseCase
import click.seichi.timetogo.usecase.`trait`.Clock
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

import java.time.LocalTime

class TimeToGo extends JavaPlugin {
  TimeToGo.instance = this

  override def onEnable(): Unit = {
    Bukkit.getServer.getLogger.info("TimeToGo is Enabled!")
  }
}

object TimeToGo {
  private var instance: TimeToGo = _

  lazy val useCase: ModeTimeUseCase = new ModeTimeUseCase {
    val clock: Clock = new Clock {
      def now: LocalTime = LocalTime.now
    }

    val repository: ModeTimeRepository = ModeTimeRepositoryImpl(instance)
  }
}
