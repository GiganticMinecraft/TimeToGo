package click.seichi.timetogo.infra

import org.scalatest.Inspectors.forAll
import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

import java.time.DayOfWeek

class DayOfWeekHelperSpec extends AnyFlatSpec with Diagrams {
  val namesToValuesMap: Set[(String, DayOfWeek)] =
    DayOfWeek.values.map(day => day.name -> day).toSet

  "DayOfWeekHelper#fromString" should "parse variant names" in {
    forAll(namesToValuesMap) {
      case (entryName, mode) =>
        assert(DayOfWeekHelper.fromString(entryName).contains(mode))
    }
  }

  it should "parse variant names when names are lowercase" in {
    val lowercaseNamesToValuesMap = namesToValuesMap.map {
      case (entryName, mode) => entryName.toLowerCase -> mode
    }

    forAll(lowercaseNamesToValuesMap) {
      case (entryName, mode) =>
        assert(DayOfWeekHelper.fromString(entryName).contains(mode))
    }
  }

  it should "parse variant names when names are uppercase" in {
    val uppercaseNamesToValuesMap = namesToValuesMap.map {
      case (entryName, mode) => entryName.toUpperCase -> mode
    }

    forAll(uppercaseNamesToValuesMap) {
      case (entryName, mode) =>
        assert(DayOfWeekHelper.fromString(entryName).contains(mode))
    }
  }
}
