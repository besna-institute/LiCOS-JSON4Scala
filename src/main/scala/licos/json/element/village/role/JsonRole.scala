package licos.json.element.village.role

import java.util.List as JList

import licos.json.element.village.iri.RoleContext
import licos.json.element.village.{JsonBoardResult, JsonName}
import licos.json.validation.village.RoleValidation

import scala.jdk.CollectionConverters.*

final case class JsonRole(
    `@context`:      String,
    `@id`:           String,
    name:            JsonName,
    image:           String,
    isMine:          Boolean,
    numberOfPlayers: Int,
    board:           Seq[JsonBoardResult]
) extends JsonAbstractRole(
      `@context`: String,
      `@id`:      String,
      name:       JsonName,
      image:      String
    ) {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      `@context`:      String,
      `@id`:           String,
      name:            JsonName,
      image:           String,
      isMine:          Boolean,
      numberOfPlayers: Int,
      board:           JList[JsonBoardResult]
  ) = {
    this(
      `@context`:           String,
      `@id`:                String,
      name:                 JsonName,
      image:                String,
      isMine:               Boolean,
      numberOfPlayers:      Int,
      board.asScala.toList: Seq[JsonBoardResult]
    )
  }

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      `@id`:           String,
      name:            JsonName,
      image:           String,
      isMine:          Boolean,
      numberOfPlayers: Int,
      board:           Seq[JsonBoardResult]
  ) = {
    this(
      RoleContext.iri: String,
      `@id`:           String,
      name:            JsonName,
      image:           String,
      isMine:          Boolean,
      numberOfPlayers: Int,
      board:           Seq[JsonBoardResult]
    )
  }

}

object JsonRole {

  import play.api.libs.json.*
  import play.api.libs.functional.syntax.*

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonRole] = (
    (JsPath \ "@context").read[String](RoleValidation.`@context`) and
      (JsPath \ "@id").read[String](RoleValidation.`@id`) and
      (JsPath \ "name").read[JsonName] and
      (JsPath \ "image").read[String](RoleValidation.image) and
      (JsPath \ "isMine").read[Boolean] and
      (JsPath \ "numberOfPlayers").read[Int](RoleValidation.numberOfPlayers) and
      (JsPath \ "board").read[Seq[JsonBoardResult]]
  )(JsonRole.apply _)

  implicit val jsonWrites: OWrites[JsonRole] = Json.writes[JsonRole]
}
