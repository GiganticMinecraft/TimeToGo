package click.seichi.timetogo.model

import org.scalatest.Inspectors.forAll
import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

class GameModeSpec extends AnyFlatSpec with Diagrams {
  val namesToValuesMap: Set[(String, GameMode)] = GameMode.values.map(mode => mode.entryName -> mode)

  "GameMode#fromString" should "parse variant names" in {
    forAll(namesToValuesMap) { case (entryName, mode) =>
      assert(GameMode.fromString(entryName).contains(mode))
    }
  }

  it should "parse variant names when names are lowercase" in {
    val lowercaseNamesToValuesMap = namesToValuesMap
      .map{ case (entryName, mode) => entryName.toLowerCase -> mode}

    forAll(lowercaseNamesToValuesMap) { case (entryName, mode) =>
      assert(GameMode.fromString(entryName).contains(mode))
    }
  }

  it should "parse variant names when names are uppercase" in {
    val uppercaseNamesToValuesMap = namesToValuesMap
      .map { case (entryName, mode) => entryName.toUpperCase -> mode }

    forAll(uppercaseNamesToValuesMap) { case (entryName, mode) =>
      assert(GameMode.fromString(entryName).contains(mode))
    }
  }
}
