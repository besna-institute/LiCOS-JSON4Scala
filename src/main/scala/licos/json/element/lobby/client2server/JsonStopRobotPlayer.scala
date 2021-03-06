package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.AvatarValidation

final case class JsonStopRobotPlayer(`type`: String, token: Seq[String]) extends TypeSystem(`type`) {

  override protected def validType: String = JsonStopRobotPlayer.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: Seq[String]) = {
    this(JsonStopRobotPlayer.`type`, token)
  }

}

object JsonStopRobotPlayer {

  val `type`: String = "stopRobotPlayer"

  import play.api.libs.functional.syntax.*
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json.*

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonStopRobotPlayer] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[Seq[String]](Reads.seq[String](AvatarValidation.token))
  )(JsonStopRobotPlayer.apply _)

  implicit val jsonWrites: OWrites[JsonStopRobotPlayer] = Json.writes[JsonStopRobotPlayer]

}
