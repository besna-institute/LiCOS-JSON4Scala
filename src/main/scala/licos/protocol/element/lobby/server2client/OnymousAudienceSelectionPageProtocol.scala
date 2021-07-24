package licos.protocol.element.lobby.server2client

import licos.json.element.lobby.server2client.JsonOnymousAudienceSelectionPage
import play.api.libs.json.{JsValue, Json}

final case class OnymousAudienceSelectionPageProtocol(avatar: Seq[AvatarInfoProtocol])
    extends Server2ClientLobbyMessageProtocol {

  override def hashCode(): Int = 523006

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: OnymousAudienceSelectionPageProtocol =>
        protocol.avatar == avatar
      case _ => false
    }
  }

  private lazy val json: Option[JsonOnymousAudienceSelectionPage] = {
    Some(
      new JsonOnymousAudienceSelectionPage(
        avatar.flatMap(_.jsonWithoutType.toList)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object OnymousAudienceSelectionPageProtocol {
  def read(json: JsonOnymousAudienceSelectionPage): Option[OnymousAudienceSelectionPageProtocol] = {
    Some(
      OnymousAudienceSelectionPageProtocol(
        json.avatar.flatMap(j => AvatarInfoProtocol.read(j).toList)
      )
    )
  }
}
