package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonAdvancedSearch
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for an advanced search.
  *
  * @author
  *   Kotaro Sakamoto
  */
trait AdvancedSearchAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box
    *   a box.
    * @param advancedSearch
    *   a JSON message.
    * @return
    *   either play.api.libs.json.JsValue.
    */
  def process(box: BOX, advancedSearch: JsonAdvancedSearch): Either[JsValue, JsValue]
}

object AdvancedSearchAnalysisEngine {

  /** Advanced-search analysis engine name.
    */
  val name:         String  = "lobby.client2server.AdvancedSearchAnalysisEngine"
  val isFromServer: Boolean = false
}
