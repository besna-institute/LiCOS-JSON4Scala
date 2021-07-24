package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonPong
import play.api.libs.json.{JsValue, Json}

final case class PongProtocol(token: UUID, id: UUID) extends Client2ServerLobbyMessageProtocol {

  override def hashCode(): Int = 521021

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: PongProtocol =>
        protocol.token == token &&
          protocol.id == id
      case _ => false
    }
  }

  private lazy val json: Option[JsonPong] = {
    Some(
      new JsonPong(
        token.toString,
        id.toString
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object PongProtocol {

  def read(jsonPong: JsonPong): Option[PongProtocol] = {
    Some(
      PongProtocol(
        UUID.fromString(jsonPong.token),
        UUID.fromString(jsonPong.id)
      )
    )
  }

}
