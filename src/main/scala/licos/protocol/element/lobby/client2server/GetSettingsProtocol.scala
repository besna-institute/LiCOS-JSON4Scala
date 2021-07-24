package licos.protocol.element.lobby.client2server

import licos.json.element.lobby.client2server.JsonGetSettings
import play.api.libs.json.{JsValue, Json}

final case class GetSettingsProtocol() extends Client2ServerLobbyMessageProtocol {

  override def hashCode(): Int = 521016

  override def equals(obj: Any): Boolean = obj.isInstanceOf[GetSettingsProtocol]

  private lazy val json: Option[JsonGetSettings] = {
    Some(new JsonGetSettings())
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object GetSettingsProtocol {

  def read(json: JsonGetSettings): Option[GetSettingsProtocol] = {
    Some(GetSettingsProtocol())
  }

}
