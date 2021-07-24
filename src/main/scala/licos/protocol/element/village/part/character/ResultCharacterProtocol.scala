package licos.protocol.element.village.part.character

import java.net.URL
import java.util.{Locale, UUID}

import licos.json.element.village.character.JsonResultCharacter
import licos.json.element.village.iri.CharacterContext
import licos.knowledge.{Character, Outcome, Role, Status}
import licos.protocol.element.village.part.AvatarProtocol
import licos.protocol.element.village.part.role.SimpleRoleProtocol
import licos.util.LiCOSOnline

final case class ResultCharacterProtocol(
    character:   Character,
    isMine:      Boolean,
    role:        Role,
    status:      Status,
    result:      Outcome,
    token:       UUID,
    avatarName:  String,
    avatarImage: URL,
    villageId:   Long,
    language:    Locale
) {

  override def hashCode(): Int = 533002

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: ResultCharacterProtocol =>
        protocol.character == character &&
          protocol.isMine == isMine &&
          protocol.role == role &&
          protocol.status == status &&
          protocol.result == result &&
          protocol.token == token &&
          protocol.avatarName == avatarName &&
          protocol.avatarImage == avatarImage &&
          protocol.villageId == villageId &&
          protocol.language == language
      case _ => false
    }
  }

  lazy val json: JsonResultCharacter = {
    val `@id`: String = LiCOSOnline.state(villageId, "")
    JsonResultCharacter(
      CharacterContext.iri,
      `@id`,
      character.getId,
      character.name.json(Option(language)),
      character.icon,
      isMine,
      SimpleRoleProtocol(role, villageId, language).json(`@id`),
      status.label,
      result.label,
      AvatarProtocol(token, avatarName, avatarImage).json(`@id`)
    )
  }

}
