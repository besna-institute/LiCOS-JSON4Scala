package licos.protocol.element.village.part.character

import java.util.Locale

import licos.json.element.village.character.JsonSimpleCharacter
import licos.json.element.village.iri.CharacterContext
import licos.knowledge.Character
import licos.util.LiCOSOnline

final case class SimpleCharacterProtocol(character: Character, villageId: Long, language: Locale) {

  override def hashCode(): Int = 533004

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: SimpleCharacterProtocol =>
        protocol.character == character &&
          protocol.villageId == villageId &&
          protocol.language == language
      case _ => false
    }
  }

  def json(`@id`: String): JsonSimpleCharacter = {
    JsonSimpleCharacter(
      CharacterContext.iri,
      LiCOSOnline
        .state(villageId, `@id`.concat(s"/character#${character.getId.toString}")),
      character.getId,
      character.name.json(Option(language)),
      character.icon
    )
  }
}
