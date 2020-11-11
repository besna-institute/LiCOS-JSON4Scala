package licos.json.engine.analysis.village.client2server

import licos.json.element.village.client2server.JsonChatFromClient
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for chat from client.
  *
  * @author Kotaro Sakamoto
  */
trait ChatAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param chatFromClient a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, chatFromClient: JsonChatFromClient): Either[JsValue, JsValue]

}

object ChatAnalysisEngine {

  /** Chat analysis engine name.
    */
  val name:         String  = "village.client2server.ChatAnalysisEngine"
  val isFromServer: Boolean = false

}
