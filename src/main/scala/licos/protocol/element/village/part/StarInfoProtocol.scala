package licos.protocol.element.village.part

import java.time.OffsetDateTime
import java.util.UUID

import licos.json.element.village.client2server.JsonStarInfo
import licos.json.element.village.iri.StarContext
import licos.util.LiCOSOnline

final case class StarInfoProtocol(
    villageId:       Long,
    token:           UUID,
    serverTimestamp: OffsetDateTime,
    clientTimestamp: OffsetDateTime,
    isMarked:        Boolean
) {

  override def hashCode(): Int = 533015

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: StarInfoProtocol =>
        protocol.villageId == villageId &&
          protocol.token == token &&
          protocol.serverTimestamp == serverTimestamp &&
          protocol.clientTimestamp == clientTimestamp &&
          protocol.isMarked == isMarked
      case _ => false
    }
  }

  lazy val json: JsonStarInfo = {
    JsonStarInfo(
      StarContext.iri,
      LiCOSOnline.state(villageId, "star"),
      token.toString,
      serverTimestamp.toString,
      clientTimestamp.toString,
      isMarked
    )
  }

}
