package protocol.engine.async.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.LeaveWaitingPageProtocol
import licos.protocol.engine.async.analysis.lobby.client2server.LeaveWaitingPageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.example.client2server.LeaveWaitingPage
import protocol.engine.lobby.LobbyBox

import scala.concurrent.{ExecutionContext, Future}

final class LeaveWaitingPageAE extends LeaveWaitingPageAnalysisEngine {
  override def process(box: LobbyBOX, leaveWaitingPageProtocol: LeaveWaitingPageProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[Option[LobbyMessageProtocol]] = {
    box match {
      case _: LobbyBox => Future.successful(Some(LobbyMessageTestProtocol(LeaveWaitingPage.`type`)))
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
