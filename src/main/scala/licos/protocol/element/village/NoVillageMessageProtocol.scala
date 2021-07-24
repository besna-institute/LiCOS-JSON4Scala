package licos.protocol.element.village

import play.api.libs.json.JsValue

final case class NoVillageMessageProtocol() extends VillageMessageProtocol {

  override def hashCode(): Int = 530000

  override def equals(obj: Any): Boolean = obj.isInstanceOf[NoVillageMessageProtocol]

  override def toJsonOpt: Option[JsValue] = Option.empty[JsValue]
}
