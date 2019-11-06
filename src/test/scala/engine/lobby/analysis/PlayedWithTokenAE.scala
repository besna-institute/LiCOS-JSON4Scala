package engine.lobby.analysis

import element.JsonTest
import engine.lobby.LobbyBox
import engine.lobby.example.PlayedWithToken
import licos.json.element.lobby.JsonPlayedWithToken
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2server.PlayedWithTokenAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class PlayedWithTokenAE extends PlayedWithTokenAnalysisEngine {
  override def process(box: BOX, playedWithToken: JsonPlayedWithToken): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(PlayedWithToken.`type`)))
      case _ => None
    }
  }
}
