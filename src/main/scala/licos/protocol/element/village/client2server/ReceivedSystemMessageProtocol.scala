package licos.protocol.element.village.client2server

import java.util.UUID

import licos.json.element.village.receipt.JsonReceivedSystemMessage
import licos.knowledge.{Data2Knowledge, Phase}
import play.api.libs.json.{JsValue, Json}

final case class ReceivedSystemMessageProtocol(token: UUID, villageId: Long, phase: Phase, day: Int)
    extends ReceivedMessageProtocol {

  override def hashCode(): Int = 532013

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: ReceivedSystemMessageProtocol =>
        protocol.token == token &&
          protocol.villageId == villageId &&
          protocol.phase == phase &&
          protocol.day == day
      case _ => false
    }
  }

  private lazy val json: Option[JsonReceivedSystemMessage] = {
    Some(new JsonReceivedSystemMessage(token.toString, villageId, phase.label, day))
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object ReceivedSystemMessageProtocol {

  def read(json: JsonReceivedSystemMessage): Option[ReceivedSystemMessageProtocol] = {
    Data2Knowledge.phaseOpt(json.phase).map { phase: Phase =>
      ReceivedSystemMessageProtocol(UUID.fromString(json.token), json.villageId, phase, json.day)
    }
  }

}
