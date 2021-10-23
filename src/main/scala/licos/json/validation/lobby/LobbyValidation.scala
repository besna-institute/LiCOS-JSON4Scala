package licos.json.validation.lobby

object LobbyValidation {

  import play.api.libs.json.*
  import play.api.libs.json.Reads.{max, min, pattern}
  import play.api.libs.functional.syntax.*

  val lobby: Reads[String] = pattern("""(?:human|robot) player|(?:an)?onymous audience""".r)
  val page:  Reads[Int]    = min(1) keepAnd max(Int.MaxValue)
}
