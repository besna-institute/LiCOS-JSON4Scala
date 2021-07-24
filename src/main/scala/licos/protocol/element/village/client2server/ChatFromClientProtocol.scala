package licos.protocol.element.village.client2server

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonChatFromClient
import licos.knowledge.{Character, Data2Knowledge, PlayerChatChannel, Role}
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

  override def hashCode(): Int = 532004

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: ChatFromClientProtocol =>
        protocol.village == village &&
          protocol.channel == channel &&
          protocol.text == text &&
          protocol.isOver == isOver &&
          protocol.myCharacter == myCharacter &&
          protocol.myRole == myRole
      case _ => false
    }
  }

  private lazy val json: Option[JsonChatFromClient] = {
    server2logger.ChatFromClientProtocol(village, channel, text, isOver, myCharacter, myRole, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def forLogger(extensionalDisclosureRange: Seq[StatusCharacterProtocol]): server2logger.ChatFromClientProtocol = {
    server2logger.ChatFromClientProtocol(
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
