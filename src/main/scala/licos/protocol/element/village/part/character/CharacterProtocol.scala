package licos.protocol.element.village.part.character

import java.util.Locale

import licos.json.element.village.character.JsonCharacter
import licos.json.element.village.iri.CharacterContext
import licos.knowledge.{Character, Status}
import licos.protocol.element.village.part.UpdateProtocol
import licos.util.LiCOSOnline

final case class CharacterProtocol(
    character: Character,
    villageId: Long,
    language:  Locale,
    isMine:    Boolean,
    status:    Status,
    update:    UpdateProtocol,
    isAChoice: Boolean
) {

  override def hashCode(): Int = 533001

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: CharacterProtocol =>
        protocol.character == character &&
          protocol.villageId == villageId &&
          protocol.language == language &&
          protocol.isMine == isMine &&
          protocol.status == status &&
          protocol.update == update &&
          protocol.isAChoice == isAChoice
      case _ => false
    }
  }

  lazy val json: JsonCharacter = {
    val `@id`: String = LiCOSOnline.state(villageId, s"character#${character.getId.toString}")
    JsonCharacter(
      CharacterContext.iri,
      `@id`,
      character.getId,
      character.name.json(Option(language)),
      character.icon,
      isMine,
      status.label,
      update.json(`@id`),
      isAChoice
    )
  }

}
