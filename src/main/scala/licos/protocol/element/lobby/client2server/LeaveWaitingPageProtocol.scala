package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonLeaveWaitingPage
import licos.knowledge.{Data2Knowledge, Lobby}
import play.api.libs.json.{JsValue, Json}

final case class LeaveWaitingPageProtocol(token: UUID, villageId: Long, lobby: Lobby)
    extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonLeaveWaitingPage] = {
    Some(
      new JsonLeaveWaitingPage(
        token.toString,
        villageId,
        lobby.label
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

}

object LeaveWaitingPageProtocol {

  def read(json: JsonLeaveWaitingPage): Option[LeaveWaitingPageProtocol] = {
    Data2Knowledge
      .lobbyOpt(json.lobby)
      .map { lobby: Lobby =>
        LeaveWaitingPageProtocol(
          UUID.fromString(json.token),
          json.villageId,
          lobby
        )
      }
  }

}
