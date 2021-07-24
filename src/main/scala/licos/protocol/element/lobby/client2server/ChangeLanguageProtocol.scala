package licos.protocol.element.lobby.client2server

import java.util.Locale

import licos.json.element.lobby.client2server.JsonChangeLanguage
import play.api.libs.json.{JsValue, Json}

final case class ChangeLanguageProtocol(language: Locale) extends Client2ServerLobbyMessageProtocol {

  override def hashCode(): Int = 521005

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: ChangeLanguageProtocol =>
        protocol.language == language
      case _ => false
    }
  }

  private lazy val json: Option[JsonChangeLanguage] = {
    Some(
      new JsonChangeLanguage(
        language.getLanguage
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object ChangeLanguageProtocol {

  def read(json: JsonChangeLanguage): Option[ChangeLanguageProtocol] = {
    Some(ChangeLanguageProtocol(new Locale(json.language)))
  }

}
