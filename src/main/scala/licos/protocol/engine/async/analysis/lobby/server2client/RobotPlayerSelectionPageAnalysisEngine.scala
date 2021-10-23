package licos.protocol.engine.async.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.RobotPlayerSelectionPageProtocol
import licos.protocol.engine.async.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait RobotPlayerSelectionPageAnalysisEngine extends LobbyMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, robotPlayerSelectionPageProtocol: RobotPlayerSelectionPageProtocol)(implicit
      ec:          ExecutionContext
  ): Future[Option[LobbyMessageProtocol]]
}

object RobotPlayerSelectionPageAnalysisEngine {
  val name: String = "lobby.server2client.RobotPlayerSelectionPage"
}
