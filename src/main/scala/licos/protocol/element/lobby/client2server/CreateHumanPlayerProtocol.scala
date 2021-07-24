package licos.protocol.element.lobby.client2server

import java.net.URL
import java.util.Locale

import licos.json.element.lobby.client2server.JsonCreateHumanPlayer
import play.api.libs.json.{JsValue, Json}

final case class CreateHumanPlayerProtocol(name: String, image: URL, language: Locale)
    extends Client2ServerLobbyMessageProtocol {

  override def hashCode(): Int = 521009

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: CreateHumanPlayerProtocol =>
        protocol.name == name &&
          protocol.image == image &&
          protocol.language == language
      case _ => false
    }
  }

  private lazy val json: Option[JsonCreateHumanPlayer] = {
    Some(
      new JsonCreateHumanPlayer(
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

object CreateHumanPlayerProtocol {

  def read(json: JsonCreateHumanPlayer): Option[CreateHumanPlayerProtocol] = {
    Some(
      CreateHumanPlayerProtocol(
        json.name,
        new URL(json.image),
        new Locale(json.language)
      )
    )
  }

}
