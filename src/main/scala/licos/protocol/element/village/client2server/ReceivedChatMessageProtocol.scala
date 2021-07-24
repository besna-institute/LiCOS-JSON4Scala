package licos.protocol.element.village.client2server

import java.time.OffsetDateTime
import java.util.UUID

import licos.json.element.village.receipt.JsonReceivedChatMessage
import play.api.libs.json.{JsValue, Json}

final case class ReceivedChatMessageProtocol(
    token:           UUID,
    villageId:       Long,
    serverTimestamp: OffsetDateTime,
    clientTimestamp: OffsetDateTime
) extends ReceivedMessageProtocol {

  override def hashCode(): Int = 532011

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: ReceivedChatMessageProtocol =>
        protocol.token == token &&
          protocol.villageId == villageId &&
          protocol.serverTimestamp == serverTimestamp &&
          protocol.clientTimestamp == clientTimestamp
      case _ => false
    }
  }

  private lazy val json: Option[JsonReceivedChatMessage] = {
    Some(new JsonReceivedChatMessage(token.toString, villageId, serverTimestamp.toString, clientTimestamp.toString))
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object ReceivedChatMessageProtocol {

  def read(json: JsonReceivedChatMessage): Option[ReceivedChatMessageProtocol] = {
    Some(
      ReceivedChatMessageProtocol(
        UUID.fromString(json.token),
        json.villageId,
        OffsetDateTime.parse(json.serverTimestamp),
        OffsetDateTime.parse(json.clientTimestamp)
      )
    )
  }

}
