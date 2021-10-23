package licos.protocol.element.village.client2server

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonChatFromClient
import licos.knowledge.{Character, Data2Knowledge, PlayerChatChannel, Role}
import licos.protocol.element.village.client2server.server2logger.ChatFromClientProtocol4Logger
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import play.api.libs.json.{JsValue, Json}

final case class ChatFromClientProtocol(
    village:     VillageInfo,
    channel:     PlayerChatChannel,
    text:        String,
    isOver:      Boolean,
    myCharacter: Character,
    myRole:      Role
) extends Client2ServerVillageMessageProtocol {

  private lazy val json: Option[JsonChatFromClient] = {
    ChatFromClientProtocol4Logger(village, channel, text, isOver, myCharacter, myRole, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def forLogger(extensionalDisclosureRange: Seq[StatusCharacterProtocol]): ChatFromClientProtocol4Logger = {
    ChatFromClientProtocol4Logger(
      village:                    VillageInfo,
      channel:                    PlayerChatChannel,
      text:                       String,
      isOver:                     Boolean,
      myCharacter:                Character,
      myRole:                     Role,
      extensionalDisclosureRange: Seq[StatusCharacterProtocol]
    )
  }

}

object ChatFromClientProtocol {

  def read(json: JsonChatFromClient, villageInfoFromLobby: VillageInfoFromLobby): Option[ChatFromClientProtocol] = {
    VillageInfoFactory
      .createOpt(villageInfoFromLobby, json.base)
      .flatMap { village: VillageInfo =>
        for {
          channel     <- Data2Knowledge.playerChatChannelOpt(json.base.intensionalDisclosureRange)
          myCharacter <- Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
          myRole      <- village.composition.parse(json.myCharacter.role.name.en)
        } yield {
          ChatFromClientProtocol(
            village,
            channel,
            json.text.`@value`,
            json.isOver,
            myCharacter,
            myRole
          )
        }
      }
  }

}
