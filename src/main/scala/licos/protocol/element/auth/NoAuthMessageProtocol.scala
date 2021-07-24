package licos.protocol.element.auth

import play.api.libs.json.JsValue

final case class NoAuthMessageProtocol() extends AuthMessageProtocol {

  override def hashCode(): Int = 510000

  override def equals(obj: Any): Boolean = obj.isInstanceOf[NoAuthMessageProtocol]

  override def toJsonOpt: Option[JsValue] = Option.empty[JsValue]
}
