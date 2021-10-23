package licos.protocol.element.village.client2server

import java.net.URL
import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.JsonOnymousAudienceChat
import licos.protocol.element.village.client2server.server2logger.OnymousAudienceChatFromClientProtocol4Logger
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import play.api.libs.json.{JsValue, Json}

final case class OnymousAudienceChatFromClientProtocol(
    village:       VillageInfo,
    text:          String,
    myAvatarName:  String,
    myAvatarImage: URL
) extends Client2ServerVillageMessageProtocol {

  private lazy val json: Option[JsonOnymousAudienceChat] = {
    OnymousAudienceChatFromClientProtocol4Logger(village, text, myAvatarName, myAvatarImage, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def forLogger(
      extensionalDisclosureRange: Seq[StatusCharacterProtocol]
  ): OnymousAudienceChatFromClientProtocol4Logger = {
    OnymousAudienceChatFromClientProtocol4Logger(
      village:                    VillageInfo,
      text:                       String,
      myAvatarName:               String,
      myAvatarImage:              URL,
      extensionalDisclosureRange: Seq[StatusCharacterProtocol]
    )
  }

}

object OnymousAudienceChatFromClientProtocol {

  def read(
      json:                 JsonOnymousAudienceChat,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[OnymousAudienceChatFromClientProtocol] = {
    if (!json.isFromServer) {
      VillageInfoFactory
        .createOpt(villageInfoFromLobby, json.base)
        .map { village: VillageInfo =>
          OnymousAudienceChatFromClientProtocol(
            village,
            json.text.`@value`,
            json.avatar.name,
            new URL(json.avatar.image)
          )
        }
    } else {
      Option.empty[OnymousAudienceChatFromClientProtocol]
    }
  }

}
