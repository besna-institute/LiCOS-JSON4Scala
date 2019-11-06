package engine.village.analysis.client2server

import engine.village.VillageBox
import engine.village.example.client2server.ReceivedSystemMessage
import element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.ReceivedSystemMessageAnalysisEngine
import licos.json.element.village.receipt.JsonReceivedSystemMessage
import play.api.libs.json.{JsValue, Json}

class ReceivedSystemMessageAE extends ReceivedSystemMessageAnalysisEngine {
  override def process(box: BOX, receivedSystemMessage: JsonReceivedSystemMessage): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(ReceivedSystemMessage.`type`)))
      case _ => None
    }
  }
}
