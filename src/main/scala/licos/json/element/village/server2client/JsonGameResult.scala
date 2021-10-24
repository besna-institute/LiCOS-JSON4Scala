package licos.json.element.village.server2client

import java.util.List as JList

import licos.json.element.Element
import licos.json.element.village.character.JsonResultCharacter
import licos.json.element.village.role.JsonResultRole
import licos.json.element.village.{JsonBase, JsonElement}
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Format, JsPath}

import scala.jdk.CollectionConverters.*

final case class JsonGameResult private (base: JsonBase, sub: JsonSubGameResult) extends JsonElement with Element {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(base: JsonBase, character: Seq[JsonResultCharacter], role: Seq[JsonResultRole]) = {
    this(
      base: JsonBase,
      JsonSubGameResult(
        character.sortWith { (a1: JsonResultCharacter, a2: JsonResultCharacter) =>
          a1.name.en < a2.name.en
        }.sortBy(!_.isMine): Seq[JsonResultCharacter],
        role.sortWith { (r1: JsonResultRole, r2: JsonResultRole) =>
          r1.name.en < r2.name.en
        }: Seq[JsonResultRole]
      )
    )
  }

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(base: JsonBase, character: JList[JsonResultCharacter], role: JList[JsonResultRole]) = {
    this(
      base: JsonBase,
      character.asScala.sortWith { (a1: JsonResultCharacter, a2: JsonResultCharacter) =>
        a1.name.en < a2.name.en
      }.sortBy(!_.isMine).toList: Seq[JsonResultCharacter],
      role.asScala.sortWith { (r1: JsonResultRole, r2: JsonResultRole) =>
        r1.name.en < r2.name.en
      }.toList: Seq[JsonResultRole]
    )
  }

  def character: Seq[JsonResultCharacter] =
    sub.character.sortWith { (a1: JsonResultCharacter, a2: JsonResultCharacter) =>
      a1.name.en < a2.name.en
    }.sortBy(!_.isMine)

  def role: Seq[JsonResultRole] = sub.role.sortWith { (r1: JsonResultRole, r2: JsonResultRole) =>
    r1.name.en < r2.name.en
  }

}

object JsonGameResult {

  def apply(base: JsonBase, sub: JsonSubGameResult): JsonGameResult = {
    new JsonGameResult(base, sub)
  }

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonFormat: Format[JsonGameResult] = (
    JsPath.format[JsonBase] and
      JsPath.format[JsonSubGameResult]
  )(JsonGameResult.apply, unlift(JsonGameResult.unapply))
}

final case class JsonSubGameResult(character: Seq[JsonResultCharacter], role: Seq[JsonResultRole])

object JsonSubGameResult {

  import play.api.libs.functional.syntax.*
  import play.api.libs.json.*

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonSubGameResult] = (
    (JsPath \ "character").read[Seq[JsonResultCharacter]] and
      (JsPath \ "role").read[Seq[JsonResultRole]]
  )(JsonSubGameResult.apply _)

  implicit val jsonWrites: OWrites[JsonSubGameResult] = Json.writes[JsonSubGameResult]
}
