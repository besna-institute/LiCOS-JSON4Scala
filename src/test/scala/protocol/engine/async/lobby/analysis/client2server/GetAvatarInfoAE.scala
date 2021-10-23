package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.GetAvatarInfoProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.GetAvatarInfoAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.GetAvatarInfo
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class GetAvatarInfoAE extends GetAvatarInfoAnalysisEngine {
  override def process(box: LobbyBOX, getAvatarInfoProtocol: GetAvatarInfoProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[Option[LobbyMessageProtocol]] = {
    box match {
      case _: LobbyBox => Future.successful(Some(LobbyMessageTestProtocol(GetAvatarInfo.`type`)))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
