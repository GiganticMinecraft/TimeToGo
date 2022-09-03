package click.seichi.timetogo.model

trait ModeTimeRepository {
  def list: List[ModeTime]
}
