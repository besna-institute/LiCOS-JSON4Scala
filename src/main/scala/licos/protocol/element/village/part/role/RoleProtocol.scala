package licos.protocol.element.village.part.role

import java.util.Locale

import licos.json.element.village.iri.RoleContext
import licos.json.element.village.role.JsonRole
import licos.knowledge.Role
import licos.protocol.element.village.part.BoardResultProtocol
import licos.util.LiCOSOnline

final case class RoleProtocol(
    role:            Role,
    isMine:          Boolean,
    numberOfPlayers: Int,
    board:           Seq[BoardResultProtocol],
    villageId:       Long,
    language:        Locale
) {

  override def hashCode(): Int = 533007

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: RoleProtocol =>
        protocol.role == role &&
          protocol.isMine == isMine &&
          protocol.numberOfPlayers == numberOfPlayers &&
          protocol.board == board &&
          protocol.villageId == villageId &&
          protocol.language == language
      case _ => false
    }
  }

  lazy val json: JsonRole = {
    val `@id`: String = LiCOSOnline.state(villageId, s"role#${role.name.en.toLowerCase(Locale.ENGLISH)}")
    JsonRole(
      RoleContext.iri,
      `@id`,
      role.name.json(Option(language)),
      role.icon,
      isMine,
      numberOfPlayers,
      board.map(_.json(`@id`))
    )
  }

}
