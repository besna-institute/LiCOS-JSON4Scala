package licos.json.element.village

import licos.json.element.Element
import licos.json.validation.village.ErrorValidation
import play.api.libs.functional.syntax.{unlift, *}
import play.api.libs.json.{Format, JsPath}

final case class JsonError private (base: JsonBase, sub: JsonSubError) extends JsonElement with Element {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(base: JsonBase, content: JsonName, severity: String, source: String, isFromServer: Boolean) = {
    this(
      base: JsonBase,
      JsonSubError(
        content:      JsonName,
        severity:     String,
        source:       String,
        isFromServer: Boolean
      )
    )
  }

  def content:      JsonName = sub.content
  def severity:     String   = sub.severity
  def source:       String   = sub.source
  def isFromServer: Boolean  = sub.isFromServer

}

object JsonError {

  def apply(base: JsonBase, sub: JsonSubError): JsonError = {
    new JsonError(base, sub)
  }

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonFormat: Format[JsonError] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubError]
  )(JsonError.apply, unlift(JsonError.unapply))
}

final case class JsonSubError(content: JsonName, severity: String, source: String, isFromServer: Boolean)

object JsonSubError {

  import play.api.libs.json.*
  import play.api.libs.functional.syntax.*

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonSubError] = (
    (JsPath \ "content").read[JsonName] and
      (JsPath \ "severity").read[String](ErrorValidation.severity) and
      (JsPath \ "source").read[String](ErrorValidation.source) and
      (JsPath \ "isFromServer").read[Boolean]
  )(JsonSubError.apply _)

  implicit val jsonWrites: OWrites[JsonSubError] = Json.writes[JsonSubError]
}
