package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.village.{AvatarValidation, VillageValidation}

final case class JsonCreateHumanPlayer(`type`: String, name: String, image: String, language: String)
    extends TypeSystem(`type`) {

  override protected def validType: String = JsonCreateHumanPlayer.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(name: String, image: String, language: String) = {
    this(
      JsonCreateHumanPlayer.`type`,
      name,
      image,
      language
    )
  }

}

object JsonCreateHumanPlayer {

  val `type`: String = "createHumanPlayer"

  import play.api.libs.functional.syntax.*
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json.*

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonCreateHumanPlayer] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "name").read[String](AvatarValidation.name) and
      (JsPath \ "image").read[String](AvatarValidation.image) and
      (JsPath \ "language").read[String](VillageValidation.language)
  )(JsonCreateHumanPlayer.apply _)

  implicit val jsonWrites: OWrites[JsonCreateHumanPlayer] = Json.writes[JsonCreateHumanPlayer]

}
