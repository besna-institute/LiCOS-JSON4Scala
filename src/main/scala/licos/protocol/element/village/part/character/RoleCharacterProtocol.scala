package licos.protocol.element.village.part.character

import java.util.Locale

import licos.json.element.village.character.JsonRoleCharacter
import licos.json.element.village.iri.CharacterContext
import licos.knowledge.{Character, Role}
import licos.protocol.element.village.part.role.SimpleRoleProtocol
import licos.util.LiCOSOnline

final case class RoleCharacterProtocol(character: Character, role: Role, villageId: Long, language: Locale) {

  override def hashCode(): Int = 533003

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: RoleCharacterProtocol =>
        protocol.character == character &&
          protocol.role == role &&
          protocol.villageId == villageId &&
          protocol.language == language
      case _ => false
    }
  }

  lazy val json: JsonRoleCharacter = {
    val `@id`: String = LiCOSOnline.state(villageId, "/myCharacter")
    JsonRoleCharacter(
      CharacterContext.iri,
      `@id`,
      character.getId,
      character.name.json(Option(language)),
      character.icon,
      SimpleRoleProtocol(role, villageId, language).json(`@id`)
    )
  }

}
