package licos.json.engine.analysis.village.client2server

import licos.json.element.village.client2server.JsonOnymousAudienceScroll
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for scrolling.
  *
  * @author
  *   Kotaro Sakamoto
  */
trait OnymousAudienceScrollAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box
    *   a box.
    * @param scroll
    *   a JSON message.
    * @return
    *   either play.api.libs.json.JsValue.
    */
  def process(box: BOX, scroll: JsonOnymousAudienceScroll): Either[JsValue, JsValue]

}

object OnymousAudienceScrollAnalysisEngine {

  /** Onymous-audience-scroll analysis engine name.
    */
  val name:         String  = "village.client2server.OnymousAudienceScrollAnalysisEngine"
  val isFromServer: Boolean = false

}
