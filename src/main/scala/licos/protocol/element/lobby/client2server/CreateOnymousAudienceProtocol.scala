package licos.protocol.element.lobby.client2server

import java.net.URL
import java.util.Locale

import licos.json.element.lobby.client2server.JsonCreateOnymousAudience
import play.api.libs.json.{JsValue, Json}

final case class CreateOnymousAudienceProtocol(name: String, image: URL, language: Locale)
    extends Client2ServerLobbyMessageProtocol {

  override def hashCode(): Int = 521010

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: CreateOnymousAudienceProtocol =>
        protocol.name == name &&
          protocol.image == image &&
          protocol.language == language
      case _ => false
    }
  }

  private lazy val json: Option[JsonCreateOnymousAudience] = {
    Some(
      new JsonCreateOnymousAudience(
        name,
        image.toString,
        language.getLanguage
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object CreateOnymousAudienceProtocol {

  def read(json: JsonCreateOnymousAudience): Option[CreateOnymousAudienceProtocol] = {
    Some(
      CreateOnymousAudienceProtocol(
        json.name,
        new URL(json.image),
        new Locale(json.language)
      )
    )
  }

}
