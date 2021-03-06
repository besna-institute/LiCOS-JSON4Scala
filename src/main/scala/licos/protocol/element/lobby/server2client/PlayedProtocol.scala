package licos.protocol.element.lobby.server2client

import java.util.Locale

import licos.json.element.lobby.server2client.JsonPlayed
import play.api.libs.json.{JsValue, Json}

final case class PlayedProtocol(language: Locale) extends Server2ClientLobbyMessageProtocol {

  lazy val json: Option[JsonPlayed] = {
    Some(
      new JsonPlayed(
        language.getLanguage
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

}

object PlayedProtocol {

  def read(json: JsonPlayed): Option[PlayedProtocol] = {
    Some(
      PlayedProtocol(
        new Locale(json.language)
      )
    )
  }

}
