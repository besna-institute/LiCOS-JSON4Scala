package licos.protocol.element.lobby

import play.api.libs.json.JsValue

final case class NoLobbyMessageProtocol() extends LobbyMessageProtocol {

  override def hashCode(): Int = 520000

  override def equals(obj: Any): Boolean = obj.isInstanceOf[NoLobbyMessageProtocol]

  override def toJsonOpt: Option[JsValue] = Option.empty[JsValue]
}
