package licos.json.element.village

import licos.json.element.Element
import licos.json.validation.village.ChatValidation
import play.api.libs.functional.syntax.{unlift, *}
import play.api.libs.json.{Format, JsPath}

final case class JsonAnonymousAudienceChat(base: JsonBase, sub: JsonSubAnonymousAudienceChat)
    extends JsonElement
    with Element {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      base:                         JsonBase,
      isMine:                       Boolean,
      text:                         JsonChatText,
      maxLengthOfUnicodeCodePoints: Int,
      isFromServer:                 Boolean
  ) = {
    this(
      base: JsonBase,
      JsonSubAnonymousAudienceChat(
        isMine:                       Boolean,
        text:                         JsonChatText,
        maxLengthOfUnicodeCodePoints: Int,
        isFromServer:                 Boolean
      )
    )
  }

  def isMine:                       Boolean      = sub.isMine
  def text:                         JsonChatText = sub.text
  def maxLengthOfUnicodeCodePoints: Int          = sub.maxLengthOfUnicodeCodePoints
  def isFromServer:                 Boolean      = sub.isFromServer

}

object JsonAnonymousAudienceChat {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonFormat: Format[JsonAnonymousAudienceChat] = (
    JsPath.format[JsonBase] and JsPath.format[JsonSubAnonymousAudienceChat]
  )(JsonAnonymousAudienceChat.apply, unlift(JsonAnonymousAudienceChat.unapply))

}

final case class JsonSubAnonymousAudienceChat(
    isMine:                       Boolean,
    text:                         JsonChatText,
    maxLengthOfUnicodeCodePoints: Int,
    isFromServer:                 Boolean
)

object JsonSubAnonymousAudienceChat {

  import play.api.libs.json.*
  import play.api.libs.functional.syntax.*

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonSubAnonymousAudienceChat] = (
    (JsPath \ "isMine").read[Boolean] and
      (JsPath \ "text").read[JsonChatText] and
      (JsPath \ "maxLengthOfUnicodeCodePoints").read[Int](ChatValidation.maxLengthOfUnicodeCodePoints) and
      (JsPath \ "isFromServer").read[Boolean]
  )(JsonSubAnonymousAudienceChat.apply _)

  implicit val jsonWrites: OWrites[JsonSubAnonymousAudienceChat] = Json.writes[JsonSubAnonymousAudienceChat]

}
