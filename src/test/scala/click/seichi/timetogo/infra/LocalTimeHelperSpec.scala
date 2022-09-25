package click.seichi.timetogo.infra

import org.scalatest.Inspectors.forAll
import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

class LocalTimeHelperSpec extends AnyFlatSpec with Diagrams {
  "LocalTimeHelper#parse" should "parse valid strings" in {
    val timeSet = Set("00:00", "01:00", "19:00", "20:00", "22:30", "22:53")

    forAll(timeSet) { str => assert(LocalTimeHelper.parse(str).isDefined) }
  }

  it should "parse invalid strings" in {
    val timeSet = Set("0:00", "01:0", "19:70", "25:00")

    forAll(timeSet) { str => assert(LocalTimeHelper.parse(str).isEmpty) }
  }
}
