package licos.json.element.lobby.server2server

import licos.json.element.Element
import licos.json.element.lobby.server2client.JsonPlayed
import licos.json.validation.village.AvatarValidation

final case class JsonPlayedWithToken(to: String, json: JsonPlayed) extends Element

object JsonPlayedWithToken {

  val `type`: String = "playedWithToken"

  import play.api.libs.functional.syntax.*
  import play.api.libs.json.*

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonPlayedWithToken] = (
    (JsPath \ "to").read[String](AvatarValidation.token) and
      (JsPath \ "json").read[JsonPlayed]
  )(JsonPlayedWithToken.apply _)

  implicit val jsonWrites: OWrites[JsonPlayedWithToken] = Json.writes[JsonPlayedWithToken]
}
