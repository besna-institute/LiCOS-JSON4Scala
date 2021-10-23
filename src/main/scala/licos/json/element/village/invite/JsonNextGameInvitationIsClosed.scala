package licos.json.element.village.invite

import licos.json.element.lobby.TypeSystem

final case class JsonNextGameInvitationIsClosed(`type`: String) extends TypeSystem(`type`) {

  override protected def validType: String = JsonNextGameInvitationIsClosed.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this() = {
    this(JsonNextGameInvitationIsClosed.`type`)
  }
}

object JsonNextGameInvitationIsClosed {

  val `type`: String = "nextGameInvitationIsClosed"

  import play.api.libs.json.*
  import play.api.libs.json.Reads.pattern

  implicit val jsonReads: Reads[JsonNextGameInvitationIsClosed] = {
    (JsPath \ "type").read[String](pattern(`type`.r)).map(JsonNextGameInvitationIsClosed.apply)
  }

  implicit val jsonWrites: OWrites[JsonNextGameInvitationIsClosed] = Json.writes[JsonNextGameInvitationIsClosed]

}
