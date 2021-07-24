package licos.protocol.element.village.server2client

import licos.json.element.village.invite.JsonNextGameInvitation
import play.api.libs.json.{JsValue, Json}

final case class NextGameInvitationProtocol(villageId: Long) extends Server2ClientVillageMessageProtocol {

  override def hashCode(): Int = 535009

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: NextGameInvitationProtocol =>
        protocol.villageId == villageId
      case _ => false
    }
  }

  private lazy val json: Option[JsonNextGameInvitation] = {
    Some(new JsonNextGameInvitation(villageId))
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object NextGameInvitationProtocol {

  def read(json: JsonNextGameInvitation): Option[NextGameInvitationProtocol] = {
    Some(NextGameInvitationProtocol(json.villageId))
  }

}
