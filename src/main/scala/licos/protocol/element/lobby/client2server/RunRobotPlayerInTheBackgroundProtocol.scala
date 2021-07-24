package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonRunRobotPlayerInTheBackground
import play.api.libs.json.{JsValue, Json}

final case class RunRobotPlayerInTheBackgroundProtocol(token: Seq[UUID]) extends Client2ServerLobbyMessageProtocol {

  override def hashCode(): Int = 521024

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: RunRobotPlayerInTheBackgroundProtocol =>
        protocol.token == token
      case _ => false
    }
  }

  private lazy val json: Option[JsonRunRobotPlayerInTheBackground] = {
    Some(
      new JsonRunRobotPlayerInTheBackground(
        token
          .map(_.toString)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

}

object RunRobotPlayerInTheBackgroundProtocol {

  def read(json: JsonRunRobotPlayerInTheBackground): Option[RunRobotPlayerInTheBackgroundProtocol] = {
    Some(
      RunRobotPlayerInTheBackgroundProtocol(
        json.token
          .map(UUID.fromString)
      )
    )
  }

}
