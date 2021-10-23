package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.{AvatarValidation, VillageValidation}

final case class JsonPlay(`type`: String, token: String, villageId: Long) extends TypeSystem(`type`) {

  override protected def validType: String = JsonPlay.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(token: String, villageId: Long) = {
    this(JsonPlay.`type`, token, villageId)
  }
}

object JsonPlay {

  val `type`: String = "play"

  import play.api.libs.functional.syntax.*
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json.*

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonPlay] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "villageId").read[Long](VillageValidation.id)
  )(JsonPlay.apply _)

  implicit val jsonWrites: OWrites[JsonPlay] = Json.writes[JsonPlay]

}
