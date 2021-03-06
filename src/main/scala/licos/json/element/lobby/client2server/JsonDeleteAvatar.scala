package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.lobby.LobbyValidation
import licos.json.validation.village.AvatarValidation

final case class JsonDeleteAvatar(`type`: String, token: Seq[String], lobby: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonDeleteAvatar.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: Seq[String], lobby: String) = {
    this(JsonDeleteAvatar.`type`, token, lobby)
  }

}

object JsonDeleteAvatar {

  val `type`: String = "deleteAvatar"

  import play.api.libs.functional.syntax.*
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json.*

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonDeleteAvatar] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[Seq[String]](Reads.seq[String](AvatarValidation.token)) and
      (JsPath \ "lobby").read[String](LobbyValidation.lobby)
  )(JsonDeleteAvatar.apply _)

  implicit val jsonWrites: OWrites[JsonDeleteAvatar] = Json.writes[JsonDeleteAvatar]

}
