package licos.protocol.element.lobby.part

import java.util.UUID

import licos.json.element.lobby.client2server.JsonPlayerTokenInKickOutPlayer

final case class PlayerTokenInKickOutPlayerProtocol(token: UUID) {

  override def hashCode(): Int = 522007

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: PlayerTokenInKickOutPlayerProtocol =>
        protocol.token == token
      case _ => false
    }
  }

  lazy val json: Option[JsonPlayerTokenInKickOutPlayer] = {
    Some(
      JsonPlayerTokenInKickOutPlayer(
        token.toString
      )
    )
  }

}

object PlayerTokenInKickOutPlayerProtocol {

  def read(json: JsonPlayerTokenInKickOutPlayer): Option[PlayerTokenInKickOutPlayerProtocol] = {
    Some(
      PlayerTokenInKickOutPlayerProtocol(
        UUID.fromString(json.token)
      )
    )
  }

}
