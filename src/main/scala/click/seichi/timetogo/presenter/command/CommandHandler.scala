package click.seichi.timetogo.presenter.command

import click.seichi.timetogo.presenter.TimeToGo.instance
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
    Try(args(0)).filter(_.toLowerCase == "reload").foreach { _ =>
      instance.reloadConfig()
      commandSender.sendMessage(s"${ChatColor.BLUE}TimeToGoの設定を再読み込みしました。")
    }

    true
  }

  override def onTabComplete(
    commandSender: CommandSender,
    command: Command,
    alias: String,
    args: Array[String]
  ): util.List[String] = List("reload").asJava
}
