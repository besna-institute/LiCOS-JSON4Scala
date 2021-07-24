package licos.protocol.element.lobby.part

import java.net.URL
import java.util.UUID

import licos.json.element.lobby.server2client.JsonPlayerInWaitingPage

final case class PlayerInWaitingPageProtocol(
    token:       UUID,
    name:        String,
    avatarImage: URL,
    isAnonymous: Boolean,
    isHost:      Boolean,
    isMe:        Boolean
) {

  override def hashCode(): Int = 522005

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: PlayerInWaitingPageProtocol =>
        protocol.token == token &&
          protocol.name == name &&
          protocol.avatarImage == avatarImage &&
          protocol.isAnonymous == isAnonymous &&
          protocol.isHost == isHost &&
          protocol.isMe == isMe
      case _ => false
    }
  }

  lazy val json: Option[JsonPlayerInWaitingPage] = {
    Some(
      JsonPlayerInWaitingPage(
        token.toString,
        name,
        avatarImage.toString,
        isAnonymous,
        isHost,
        isMe
      )
    )
  }

}

object PlayerInWaitingPageProtocol {

  def read(json: JsonPlayerInWaitingPage): Option[PlayerInWaitingPageProtocol] = {
    Some(
      PlayerInWaitingPageProtocol(
        UUID.fromString(json.token),
        json.name,
        new URL(json.avatarImage),
        json.isAnonymous,
        json.isHost,
        json.isMe
      )
    )
  }

}
