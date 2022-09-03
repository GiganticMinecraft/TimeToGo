package click.seichi.timetogo.usecase

import click.seichi.timetogo.model.{GameMode, ModeTime, ModeTimeRepository}
import click.seichi.timetogo.usecase.`trait`.Clock
import org.scalamock.scalatest.MockFactory
import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

import java.time.LocalTime

class ModeTimeUseCaseSpec extends AnyFlatSpec with Diagrams with MockFactory {
  private val mockRepo = mock[ModeTimeRepository]
  private val mockClock = mock[Clock]
  private object useCase extends ModeTimeUseCase {
    val repository: ModeTimeRepository = mockRepo
    val clock: Clock = mockClock
  }

  "#enabledModeTime" should "find nothing when list is empty" in {
    (() => mockRepo.list).expects().returning(List()).once()
    (() => mockClock.now).expects().never()

    assert(useCase.enabledModeTime.isEmpty)
  }

  it should "find nothing when all modeTimes are in the future" in {
    val list = List(ModeTime(GameMode.Survival, LocalTime.of(12, 0)))
    (() => mockRepo.list).expects().returning(list)
    (() => mockClock.now).expects().returning(LocalTime.of(0, 0)).once()

    assert(useCase.enabledModeTime.isEmpty)
  }

  it should "find something when all modeTimes are in the past" in {
    val latest = ModeTime(GameMode.Survival, LocalTime.of(3, 0))
    val list = List(ModeTime(GameMode.Creative, LocalTime.of(0, 0)), latest)
    (() => mockRepo.list).expects().returning(list)
    (() => mockClock.now).expects().returning(LocalTime.of(12, 0)).atLeastOnce()

    assert(useCase.enabledModeTime.contains(latest))
  }

  it should "find something when various modeTimes" in {
    val latest = ModeTime(GameMode.Survival, LocalTime.of(3, 0))
    val list = List(
      ModeTime(GameMode.Creative, LocalTime.of(0, 0)),
      latest,
      ModeTime(GameMode.Spectator, LocalTime.of(15, 0))
    )
    (() => mockRepo.list).expects().returning(list)
    (() => mockClock.now).expects().returning(LocalTime.of(12, 0)).atLeastOnce()

    assert(useCase.enabledModeTime.contains(latest))
  }
}
