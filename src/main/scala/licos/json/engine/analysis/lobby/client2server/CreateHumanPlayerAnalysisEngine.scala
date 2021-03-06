package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonCreateHumanPlayer
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for creating a human player.
  *
  * @author
  *   Kotaro Sakamoto
  */
trait CreateHumanPlayerAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box
    *   a box.
    * @param createHumanPlayer
    *   a JSON message.
    * @return
    *   either play.api.libs.json.JsValue.
    */
  def process(box: BOX, createHumanPlayer: JsonCreateHumanPlayer): Either[JsValue, JsValue]
}

object CreateHumanPlayerAnalysisEngine {

  /** Create-human-player analysis engine name.
    */
  val name:         String  = "lobby.client2server.CreateHumanPlayerAnalysisEngine"
  val isFromServer: Boolean = false
}
