package licos.json.flow

import licos.json.element.Element
import licos.json.flow.utils.*
import licos.json.parser.AuthParser
import play.api.libs.json.JsValue

final class AuthFlowController extends FlowController with AuthParser {
  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
  override def flow(jsValue: JsValue): Either[JsValue, Element] = {
    parseAuthenticationAndAuthorizationRequest(jsValue) >>> {
      parseAuthenticationRequestResponse(jsValue) >>> {
        parseAuthorizationRequestResponse(jsValue)
      }
    }
  }
}
