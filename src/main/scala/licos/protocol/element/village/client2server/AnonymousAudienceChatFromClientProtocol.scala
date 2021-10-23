package licos.protocol.element.village.client2server

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.JsonAnonymousAudienceChat
import licos.protocol.element.village.client2server.server2logger.AnonymousAudienceChatFromClientProtocol4Logger
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import play.api.libs.json.{JsValue, Json}

final case class AnonymousAudienceChatFromClientProtocol(village: VillageInfo, text: String)
    extends Client2ServerVillageMessageProtocol {

  private lazy val json: Option[JsonAnonymousAudienceChat] = {
    AnonymousAudienceChatFromClientProtocol4Logger(village, text, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def forLogger(
      extensionalDisclosureRange: Seq[StatusCharacterProtocol]
  ): AnonymousAudienceChatFromClientProtocol4Logger = {
    AnonymousAudienceChatFromClientProtocol4Logger(
      village:                    VillageInfo,
      text:                       String,
      extensionalDisclosureRange: Seq[StatusCharacterProtocol]
    )
  }

}

object AnonymousAudienceChatFromClientProtocol {

  def read(
      json:                 JsonAnonymousAudienceChat,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[AnonymousAudienceChatFromClientProtocol] = {
    if (!json.isFromServer) {
      VillageInfoFactory
        .createOpt(villageInfoFromLobby, json.base)
        .map { village: VillageInfo =>
          AnonymousAudienceChatFromClientProtocol(
            village,
            json.text.`@value`
          )
        }
    } else {
      Option.empty[AnonymousAudienceChatFromClientProtocol]
    }
  }

}
