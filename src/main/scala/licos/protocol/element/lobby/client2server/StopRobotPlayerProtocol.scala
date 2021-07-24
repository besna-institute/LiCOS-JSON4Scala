package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonStopRobotPlayer
import play.api.libs.json.{JsValue, Json}

final case class StopRobotPlayerProtocol(token: Seq[UUID]) extends Client2ServerLobbyMessageProtocol {

  override def hashCode(): Int = 521026

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: StopRobotPlayerProtocol =>
        protocol.token == token
      case _ => false
    }
  }

  private lazy val json: Option[JsonStopRobotPlayer] = {
    Some(
      new JsonStopRobotPlayer(
        token
          .map(_.toString)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

}

object StopRobotPlayerProtocol {

  def read(json: JsonStopRobotPlayer): Option[StopRobotPlayerProtocol] = {
    Some(
      StopRobotPlayerProtocol(
        json.token
          .map(UUID.fromString)
      )
    )
  }

}
