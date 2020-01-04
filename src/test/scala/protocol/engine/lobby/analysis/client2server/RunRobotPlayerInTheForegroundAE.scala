package protocol.engine.lobby.analysis.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.RunRobotPlayerInTheForegroundProtocol
import licos.protocol.engine.analysis.lobby.client2server.RunRobotPlayerInTheForegroundAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}
import protocol.element.LobbyMessageTestProtocol
import protocol.engine.lobby.LobbyBox
import protocol.engine.lobby.example.client2server.RunRobotPlayerInTheForeground

import scala.util.{Failure, Success, Try}

final class RunRobotPlayerInTheForegroundAE extends RunRobotPlayerInTheForegroundAnalysisEngine {
  override def process(
      box:                                   LobbyBOX,
      runRobotPlayerInTheForegroundProtocol: RunRobotPlayerInTheForegroundProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox => Success(LobbyMessageTestProtocol(RunRobotPlayerInTheForeground.`type`))
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
