package licos.protocol.element.village.part

import licos.json.element.village.JsonChatSettings
import licos.json.element.village.iri.ChatSettingsContext
import licos.util.LiCOSOnline

final case class ChatSettingsProtocol(
    villageId:                    Long,
    maxNumberOfChatMessages:      Int,
    maxLengthOfUnicodeCodePoints: Int
) {

  lazy val json: JsonChatSettings =
    JsonChatSettings(
      ChatSettingsContext.iri,
      LiCOSOnline.state(villageId, "chatSettings"),
      maxNumberOfChatMessages,
      maxLengthOfUnicodeCodePoints
    )

}
