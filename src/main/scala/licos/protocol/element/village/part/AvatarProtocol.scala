package licos.protocol.element.village.part

import java.net.URL
import java.util.UUID

import licos.json.element.village.JsonAvatar
import licos.json.element.village.iri.AvatarContext

final case class AvatarProtocol(token: UUID, name: String, image: URL) {

  override def hashCode(): Int = 533009

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: AvatarProtocol =>
        protocol.token == token &&
          protocol.name == name &&
          protocol.image == image
      case _ => false
    }
  }

  def json(`@id`: String): JsonAvatar = JsonAvatar(
    AvatarContext.iri,
    `@id`.concat("/avatar"),
    token.toString,
    name,
    image.toString
  )

}
