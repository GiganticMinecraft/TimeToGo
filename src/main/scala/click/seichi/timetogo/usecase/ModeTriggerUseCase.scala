package click.seichi.timetogo.usecase

import click.seichi.timetogo.model.{ModeTrigger, ModeTriggerRepository}
import click.seichi.timetogo.usecase.`trait`.Clock

trait ModeTriggerUseCase {
  // region abstract members by DI

  protected def clock: Clock

  protected def repository: ModeTriggerRepository

  // endregion

  def list: List[ModeTrigger] = repository.list.sortBy(_.time)

  def findEnabled: Option[ModeTrigger] = list.findLast(trigger =>
    trigger.time.isBefore(clock.time) && trigger.daysOfWeek.contains(clock.dayOfWeek)
  )
}
