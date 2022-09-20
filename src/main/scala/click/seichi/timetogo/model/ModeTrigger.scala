package click.seichi.timetogo.model

import java.time.{DayOfWeek, LocalTime}

case class ModeTrigger(gameMode: GameMode, daysOfWeek: Set[DayOfWeek], time: LocalTime)
