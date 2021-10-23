package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.AvatarValidation

final case class JsonKickOutPlayer(`type`: String, token: String, players: Seq[JsonPlayerTokenInKickOutPlayer])
    extends TypeSystem(`type`) {

  override protected def validType: String = JsonKickOutPlayer.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String, players: Seq[JsonPlayerTokenInKickOutPlayer]) = {
    this(JsonKickOutPlayer.`type`, token, players)
  }
}

object JsonKickOutPlayer {

  val `type`: String = "kickOutPlayer"

  import play.api.libs.functional.syntax.*
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json.*

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonKickOutPlayer] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "players").read[Seq[JsonPlayerTokenInKickOutPlayer]]
  )(JsonKickOutPlayer.apply _)

  implicit val jsonWrites: OWrites[JsonKickOutPlayer] = Json.writes[JsonKickOutPlayer]

}

final case class JsonPlayerTokenInKickOutPlayer(token: String)

object JsonPlayerTokenInKickOutPlayer {

  import play.api.libs.json.*

  implicit val jsonReads: Reads[JsonPlayerTokenInKickOutPlayer] =
    (JsPath \ "token").read[String](AvatarValidation.token).map(JsonPlayerTokenInKickOutPlayer.apply)

  implicit val jsonWrites: OWrites[JsonPlayerTokenInKickOutPlayer] = Json.writes[JsonPlayerTokenInKickOutPlayer]
}
