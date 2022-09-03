package click.seichi.timetogo.usecase

import click.seichi.timetogo.model.{ModeTime, ModeTimeRepository}
import click.seichi.timetogo.usecase.`trait`.Clock

trait ModeTimeUseCase {
  // region abstract members by DI

  protected def clock: Clock

  protected def repository: ModeTimeRepository

  // endregion

  def list: List[ModeTime] = repository.list.sortBy(_.time)

  def enabledModeTime: Option[ModeTime] = list.findLast(_.time.isBefore(clock.now))
}
