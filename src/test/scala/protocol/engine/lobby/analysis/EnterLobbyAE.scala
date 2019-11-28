package protocol.engine.lobby.analysis

import licos.json.element.lobby.client2server.JsonEnterLobby
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.EnterLobbyProtocol
import licos.protocol.engine.analysis.lobby.client2server.EnterLobbyAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.{LobbyBox, NoLobbyBOXException}

import scala.util.{Failure, Success, Try}

class EnterLobbyAE extends EnterLobbyAnalysisEngine {
  override def process(box: LobbyBOX, enterLobbyProtocol: EnterLobbyProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(JsonEnterLobby.`type`))
      case _ => Failure(new NoLobbyBOXException())
    }
  }
}
