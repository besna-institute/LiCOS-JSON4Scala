package licos.json.element.lobby.server2client

import licos.json.element.lobby.{JsonVillage, TypeSystem}
import licos.json.element.village.JsonSubError
import licos.json.validation.lobby.LobbyValidation

final case class JsonLobby(`type`: String, lobby: String, villages: Seq[JsonVillage], error: Option[JsonSubError])
    extends TypeSystem(`type`) {

  override protected def validType: String = JsonLobby.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(lobby: String, village: Seq[JsonVillage], error: Option[JsonSubError]) = {
    this(JsonLobby.`type`, lobby, village, error)
  }
}

object JsonLobby {

  val `type`: String = "lobby"

  import play.api.libs.functional.syntax.*
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json.*

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonLobby] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "lobby").read[String](LobbyValidation.lobby) and
      (JsPath \ "villages").read[Seq[JsonVillage]] and
      (JsPath \ "error").readNullable[JsonSubError]
  )(JsonLobby.apply _)

  implicit val jsonWrites: OWrites[JsonLobby] = Json.writes[JsonLobby]
}
