package licos.protocol.element.village.part

import licos.json.element.village.JsonChatSettings
import licos.json.element.village.iri.ChatSettingsContext
import licos.util.LiCOSOnline

final case class ChatSettingsProtocol(
    villageId:                    Long,
    maxNumberOfChatMessages:      Int,
    maxLengthOfUnicodeCodePoints: Int
) {

  override def hashCode(): Int = 533012

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: ChatSettingsProtocol =>
        protocol.villageId == villageId &&
          protocol.maxNumberOfChatMessages == maxNumberOfChatMessages &&
          protocol.maxLengthOfUnicodeCodePoints == maxLengthOfUnicodeCodePoints
      case _ => false
    }
  }

  lazy val json: JsonChatSettings =
    JsonChatSettings(
      ChatSettingsContext.iri,
      LiCOSOnline.state(villageId, "chatSettings"),
      maxNumberOfChatMessages,
      maxLengthOfUnicodeCodePoints
    )
}
