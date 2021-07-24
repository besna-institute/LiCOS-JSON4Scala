package licos.protocol.element.lobby.client2server
import licos.json.element.lobby.client2server.JsonEnterAvatarSelectionPage
import licos.knowledge.{Data2Knowledge, Lobby}
import play.api.libs.json.{JsValue, Json}

final case class EnterAvatarSelectionPageProtocol(lobby: Lobby) extends Client2ServerLobbyMessageProtocol {

  override def hashCode(): Int = 521013

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: EnterAvatarSelectionPageProtocol =>
        protocol.lobby == lobby
      case _ => false
    }
  }

  private lazy val json: Option[JsonEnterAvatarSelectionPage] = {
    Some(
      new JsonEnterAvatarSelectionPage(
        lobby.label
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object EnterAvatarSelectionPageProtocol {

  def read(json: JsonEnterAvatarSelectionPage): Option[EnterAvatarSelectionPageProtocol] = {
    Data2Knowledge.lobbyOpt(json.lobby).map { lobby: Lobby =>
      EnterAvatarSelectionPageProtocol(
        lobby
      )
    }
  }

}
