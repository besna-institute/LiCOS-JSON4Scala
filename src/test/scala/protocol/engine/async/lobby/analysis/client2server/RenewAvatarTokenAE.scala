package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.RenewAvatarTokenProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.RenewAvatarTokenAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.RenewAvatarToken

import scala.concurrent.{ExecutionContext, Future}

final class RenewAvatarTokenAE extends RenewAvatarTokenAnalysisEngine {
  override def process(box: LobbyBOX, renewAvatarTokenProtocol: RenewAvatarTokenProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[Option[LobbyMessageProtocol]] = {
    box match {
      case _: LobbyBox => Future.successful(Some(LobbyMessageTestProtocol(RenewAvatarToken.`type`)))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
