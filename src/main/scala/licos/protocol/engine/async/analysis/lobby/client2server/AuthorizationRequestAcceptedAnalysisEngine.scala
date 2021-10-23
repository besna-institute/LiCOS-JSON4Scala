package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.AuthorizationRequestAcceptedProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait AuthorizationRequestAcceptedAnalysisEngine extends LobbyMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(
      box:                                  LobbyBOX,
      authorizationRequestAcceptedProtocol: AuthorizationRequestAcceptedProtocol
  )(implicit ec:                            ExecutionContext): Future[Option[LobbyMessageProtocol]]
}

object AuthorizationRequestAcceptedAnalysisEngine {
  val name: String = "lobby.client2server.AuthorizationRequestAccepted"
}
