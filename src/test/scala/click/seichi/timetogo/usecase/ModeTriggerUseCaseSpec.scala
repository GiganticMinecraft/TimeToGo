package click.seichi.timetogo.usecase

import click.seichi.timetogo.model.{DayOfWeek, GameMode, ModeTrigger, ModeTriggerRepository}
import click.seichi.timetogo.usecase.`trait`.Clock
import org.scalamock.scalatest.MockFactory
import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

import java.time.LocalTime

class ModeTriggerUseCaseSpec extends AnyFlatSpec with Diagrams with MockFactory {
  private val mockRepo = mock[ModeTriggerRepository]
  private val mockClock = mock[Clock]
  private object useCase extends ModeTriggerUseCase {
    val repository: ModeTriggerRepository = mockRepo
    val clock: Clock = mockClock
  }

  "#enabledModeTrigger" should "find nothing when list is empty" in {
    (() => mockRepo.list).expects().returning(List()).once()
    (() => mockClock.now_time).expects().never()
    (() => mockClock.now_day_of_week).expects().never()

    assert(useCase.findEnabled.isEmpty)
  }

  it should "find nothing when all ModeTriggers are in the future" in {
    val list = List(ModeTrigger(GameMode.Survival, Set(DayOfWeek.Sunday), LocalTime.of(12, 0)))
    (() => mockRepo.list).expects().returning(list)
    (() => mockClock.now_time).expects().returning(LocalTime.of(0, 0)).once()
    (() => mockClock.now_day_of_week).expects().never()

    assert(useCase.findEnabled.isEmpty)
  }

  it should "find nothing when all ModeTriggers are in the past but doesn't contains the WeekOfDay" in {
    val list = List(ModeTrigger(GameMode.Survival, Set(DayOfWeek.Sunday), LocalTime.of(12, 0)))
    (() => mockRepo.list).expects().returning(list)
    (() => mockClock.now_time).expects().returning(LocalTime.of(13, 0)).once()
    (() => mockClock.now_day_of_week).expects().returning(DayOfWeek.Monday.asJava).once()

    assert(useCase.findEnabled.isEmpty)
  }

  it should "find something when all ModeTriggers are in the past and contains the WeekOfDay" in {
    val latestDayOfWeek = DayOfWeek.Monday
    val latest = ModeTrigger(GameMode.Survival, Set(latestDayOfWeek), LocalTime.of(3, 0))
    val list = List(ModeTrigger(GameMode.Creative, Set(), LocalTime.of(0, 0)), latest)
    (() => mockRepo.list).expects().returning(list)
    (() => mockClock.now_time).expects().returning(LocalTime.of(12, 0)).atLeastOnce()
    (() => mockClock.now_day_of_week).expects().returning(latestDayOfWeek.asJava).atLeastOnce()

    assert(useCase.findEnabled.contains(latest))
  }

  it should "find something when various ModeTriggers" in {
    val latestDayOfWeek = DayOfWeek.Monday
    val latest = ModeTrigger(GameMode.Survival, Set(latestDayOfWeek), LocalTime.of(3, 0))
    val list = List(
      ModeTrigger(GameMode.Creative, Set(latestDayOfWeek), LocalTime.of(0, 0)),
      latest,
      ModeTrigger(GameMode.Spectator, Set(), LocalTime.of(15, 0))
    )
    (() => mockRepo.list).expects().returning(list)
    (() => mockClock.now_time).expects().returning(LocalTime.of(12, 0)).atLeastOnce()
    (() => mockClock.now_day_of_week).expects().returning(latestDayOfWeek.asJava).atLeastOnce()

    assert(useCase.findEnabled.contains(latest))
  }
}
