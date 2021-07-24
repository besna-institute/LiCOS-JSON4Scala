package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonAuthorizationRequestAccepted
import play.api.libs.json.{JsValue, Json}

final case class AuthorizationRequestAcceptedProtocol(accessToken: UUID) extends Client2ServerLobbyMessageProtocol {

  override def hashCode(): Int = 521002

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: AuthorizationRequestAcceptedProtocol =>
        protocol.accessToken == accessToken
      case _ => false
    }
  }

  private lazy val json: Option[JsonAuthorizationRequestAccepted] = {
    Some(
      new JsonAuthorizationRequestAccepted(
        accessToken.toString
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object AuthorizationRequestAcceptedProtocol {
  def read(json: JsonAuthorizationRequestAccepted): Option[AuthorizationRequestAcceptedProtocol] = {
    Some(
      AuthorizationRequestAcceptedProtocol(
        UUID.fromString(json.accessToken)
      )
    )
  }
}
