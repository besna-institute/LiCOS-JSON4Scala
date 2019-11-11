package licos.json.element.village

import licos.LiCOSOnline
import licos.bson.element.village.BsonVillage
import licos.json.element.village.iri.VillageContext
import licos.json.validation.village.VillageValidation
import org.bson.types.ObjectId

final case class JsonVillage(
    `@context`:              String,
    `@id`:                   String,
    id:                      Long,
    name:                    String,
    totalNumberOfCharacters: Int,
    lang:                    String,
    chatSettings:            JsonChatSettings
) extends JsonElement {

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(id: Long, name: String, totalNumberOfCharacters: Int, lang: String, chatSettings: JsonChatSettings) = {
    this(
      VillageContext.iri:       String,
      LiCOSOnline.stateVillage: String,
      id:                       Long,
      name:                     String,
      totalNumberOfCharacters:  Int,
      lang:                     String,
      chatSettings:             JsonChatSettings
    )
  }

  override def toBson: BsonVillage = {
    new BsonVillage(
      new ObjectId(),
      `@context`:              String,
      `@id`:                   String,
      id:                      Long,
      name:                    String,
      totalNumberOfCharacters: Int,
      lang:                    String,
      chatSettings.toBson
    )
  }
}

object JsonVillage {

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonVillage] = (
    (JsPath \ "@context").read[String](VillageValidation.`@context`) and
      (JsPath \ "@id").read[String](VillageValidation.`@id`) and
      (JsPath \ "id").read[Long](VillageValidation.id) and
      (JsPath \ "name").read[String](VillageValidation.name) and
      (JsPath \ "totalNumberOfCharacters").read[Int](VillageValidation.totalNumberOfCharacters) and
      (JsPath \ "lang").read[String](VillageValidation.lang) and
      (JsPath \ "chatSettings").read[JsonChatSettings]
  )(JsonVillage.apply _)

  implicit val jsonWrites: OWrites[JsonVillage] = Json.writes[JsonVillage]
}
