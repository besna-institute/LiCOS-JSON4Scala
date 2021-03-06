package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.lobby.LobbyValidation

final case class JsonEnterAvatarSelectionPage(`type`: String, lobby: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonEnterAvatarSelectionPage.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(lobby: String) = {
    this(JsonEnterAvatarSelectionPage.`type`, lobby)
  }

}

object JsonEnterAvatarSelectionPage {

  val `type`: String = "enterAvatarSelectionPage"

  import play.api.libs.functional.syntax.*
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json.*

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonEnterAvatarSelectionPage] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "lobby").read[String](LobbyValidation.lobby)
  )(JsonEnterAvatarSelectionPage.apply _)

  implicit val jsonWrites: OWrites[JsonEnterAvatarSelectionPage] = Json.writes[JsonEnterAvatarSelectionPage]

}
