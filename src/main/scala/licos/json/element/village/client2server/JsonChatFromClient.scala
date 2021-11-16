package licos.json.element.village.client2server

import licos.json.element.Element
import licos.json.element.village.character.{JsonRoleCharacter, JsonSimpleCharacter}
import licos.json.element.village.{JsonBase, JsonChatText, JsonElement}
import licos.json.validation.village.ChatValidation
import play.api.libs.functional.syntax.{unlift, *}
import play.api.libs.json.{Format, JsPath}

final case class JsonChatFromClient private (base: JsonBase, sub: JsonSubChatFromClient)
    extends JsonElement
    with Element {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      base:                         JsonBase,
      myCharacter:                  JsonRoleCharacter,
      character:                    JsonSimpleCharacter,
      isMine:                       Boolean,
      text:                         JsonChatText,
      maxLengthOfUnicodeCodePoints: Int,
      isOver:                       Boolean
  ) = {
    this(
      base: JsonBase,
      JsonSubChatFromClient(
        myCharacter:                  JsonRoleCharacter,
        character:                    JsonSimpleCharacter,
        isMine:                       Boolean,
        text:                         JsonChatText,
        maxLengthOfUnicodeCodePoints: Int,
        isOver:                       Boolean
      )
    )
  }

  def myCharacter:                  JsonRoleCharacter   = sub.myCharacter
  def character:                    JsonSimpleCharacter = sub.character
  def isMine:                       Boolean             = sub.isMine
  def text:                         JsonChatText        = sub.text
  def maxLengthOfUnicodeCodePoints: Int                 = sub.maxLengthOfUnicodeCodePoints
  def isOver:                       Boolean             = sub.isOver

}

object JsonChatFromClient {

  def apply(base: JsonBase, sub: JsonSubChatFromClient): JsonChatFromClient = {
    new JsonChatFromClient(base, sub)
  }

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonFormat: Format[JsonChatFromClient] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubChatFromClient]
  )(JsonChatFromClient.apply, unlift(JsonChatFromClient.unapply))
}

final case class JsonSubChatFromClient(
    myCharacter:                  JsonRoleCharacter,
    character:                    JsonSimpleCharacter,
    isMine:                       Boolean,
    text:                         JsonChatText,
    maxLengthOfUnicodeCodePoints: Int,
    isOver:                       Boolean
)

object JsonSubChatFromClient {

  import play.api.libs.functional.syntax.*
  import play.api.libs.json.*

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonSubChatFromClient] = (
    (JsPath \ "myCharacter").read[JsonRoleCharacter] and
      (JsPath \ "character").read[JsonSimpleCharacter] and
      (JsPath \ "isMine").read[Boolean] and
      (JsPath \ "text").read[JsonChatText] and
      (JsPath \ "maxLengthOfUnicodeCodePoints").read[Int](ChatValidation.maxLengthOfUnicodeCodePoints) and
      (JsPath \ "isOver").read[Boolean]
  )(JsonSubChatFromClient.apply _)

  implicit val jsonWrites: OWrites[JsonSubChatFromClient] = Json.writes[JsonSubChatFromClient]
}
