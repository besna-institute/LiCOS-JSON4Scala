package json.engine.lobby.analysis

import json.engine.lobby.LobbyBox
import json.engine.lobby.example.EnterLobby
import json.element.JsonTest
import licos.json.element.lobby.client2server.JsonEnterLobby
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.EnterLobbyAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class EnterLobbyAE extends EnterLobbyAnalysisEngine {
  override def process(box: BOX, enterLobby: JsonEnterLobby): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(EnterLobby.`type`)))
      case _ => Left(Json.toJson(enterLobby))
    }
  }
}
