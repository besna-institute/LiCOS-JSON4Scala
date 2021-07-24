package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.server2client.JsonGetAvatarInfo
import play.api.libs.json.{JsValue, Json}

final case class GetAvatarInfoProtocol(token: UUID) extends Client2ServerLobbyMessageProtocol {

  override def hashCode(): Int = 521015

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: GetAvatarInfoProtocol =>
        protocol.token == token
      case _ => false
    }
  }

  private lazy val json: Option[JsonGetAvatarInfo] = {
    Some(
      new JsonGetAvatarInfo(
        token.toString
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object GetAvatarInfoProtocol {

  def read(json: JsonGetAvatarInfo): Option[GetAvatarInfoProtocol] = {
    Some(
      GetAvatarInfoProtocol(
        UUID.fromString(json.token)
      )
    )
  }

}
