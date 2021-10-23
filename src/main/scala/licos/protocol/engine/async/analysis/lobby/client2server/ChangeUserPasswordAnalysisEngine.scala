package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeUserPasswordProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait ChangeUserPasswordAnalysisEngine extends LobbyMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, changeUserPasswordProtocol: ChangeUserPasswordProtocol)(implicit
      ec:          ExecutionContext
  ): Future[Option[LobbyMessageProtocol]]
}

object ChangeUserPasswordAnalysisEngine {
  val name: String = "lobby.client2server.ChangeUserPassword"
}
