package click.seichi.timetogo.presenter.command

import click.seichi.timetogo.presenter.TimeToGo.{instance, useCase}
import org.bukkit.ChatColor
import org.bukkit.command.{Command, CommandSender, TabExecutor}

import scala.jdk.CollectionConverters._
import scala.util.Try
import java.util

object CommandHandler extends TabExecutor {
  override def onCommand(
    commandSender: CommandSender,
    command: Command,
    alias: String,
    args: Array[String]
  ): Boolean = {
    Try(args(0)).toOption.map(_.toLowerCase) match {
      case Some(arg) if arg == "reload" =>
        instance.reloadConfig()
        commandSender.sendMessage(s"${ChatColor.BLUE}TimeToGoの設定を再読み込みしました。")
      case Some(arg) if arg == "list" =>
        val list = useCase.list
        val messages =
          if (list.isEmpty) List(s"${ChatColor.RED}設定はありません。")
          else
            List("=== TimeToGo GameMode Schedules ===") ++
              list
                .map(modeTrigger =>
                  s"${modeTrigger.daysOfWeek.toString} ${modeTrigger.time.toString}: ${modeTrigger.gameMode.entryName}"
                )
                .map(msg => s"${ChatColor.BLUE}$msg")
        commandSender.sendMessage(messages.toArray)
      case _ =>
    }

    true
  }

  override def onTabComplete(
    commandSender: CommandSender,
    command: Command,
    alias: String,
    args: Array[String]
  ): util.List[String] = List("reload", "list").asJava
}
