package protocol.engine.lobby.example.client2server

import protocol.engine.ClientToServerLobbyExample

final case class RunRobotPlayerInTheBackground(filePath: String) extends ClientToServerLobbyExample(filePath) {
  override val `type`: String = RunRobotPlayerInTheBackground.`type`
}

object RunRobotPlayerInTheBackground {
  val `type`: String = "lobby.client2server.RunRobotPlayerInTheBackground"
}
