package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.GetSettingsProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.GetSettingsAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.GetSettings
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class GetSettingsAE extends GetSettingsAnalysisEngine {
  override def process(box: LobbyBOX, getSettingsProtocol: GetSettingsProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Future.successful(LobbyMessageTestProtocol(GetSettings.`type`))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
