package licos.protocol.element.village.server2client

import licos.json.element.village.invite.JsonNextGameInvitationIsClosed
import play.api.libs.json.{JsValue, Json}

final case class NextGameInvitationIsClosedProtocol() extends Server2ClientVillageMessageProtocol {

  override def hashCode(): Int = 535008

  override def equals(obj: Any): Boolean = obj.isInstanceOf[NextGameInvitationIsClosedProtocol]

  private lazy val json: Option[JsonNextGameInvitationIsClosed] = {
    Some(new JsonNextGameInvitationIsClosed())
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object NextGameInvitationIsClosedProtocol {

  def read(json: JsonNextGameInvitationIsClosed): Option[NextGameInvitationIsClosedProtocol] = {
    Some(NextGameInvitationIsClosedProtocol())
  }

}
