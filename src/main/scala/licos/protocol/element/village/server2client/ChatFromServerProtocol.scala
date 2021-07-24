package licos.protocol.element.village.server2client

import java.time.OffsetDateTime
import java.util.UUID

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.iri.{ChatMessage, Contexts}
import licos.json.element.village.server2client.JsonChatFromServer
import licos.knowledge.{Data2Knowledge, PlayerChatChannel, ServerToClient}
import licos.protocol.element.village.part.{
  BaseProtocol,
  ChatSettingsProtocol,
  ChatTextProtocol,
  VillageProtocol,
  VotingResultDetailProtocol,
  VotingResultSummaryProtocol
}
import licos.protocol.element.village.part.character.{SimpleCharacterProtocol, StatusCharacterProtocol}
import licos.util.{LiCOSOnline, TimestampGenerator}
import play.api.libs.json.{JsValue, Json}

final case class ChatFromServerProtocol(
    village:   VillageInfo,
    channel:   PlayerChatChannel,
    character: SimpleCharacterProtocol,
    isMine:    Boolean,
    id:        Int,
    counter:   Int,
    interval:  Int,
    text:      String,
    isOver:    Boolean
) extends Server2ClientVillageMessageProtocol {

  override def hashCode(): Int = 535002

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: ChatFromServerProtocol =>
        protocol.village == village &&
          protocol.channel == channel &&
          protocol.character == character &&
          protocol.isMine == isMine &&
          protocol.id == id &&
          protocol.counter == counter &&
          protocol.interval == interval &&
          protocol.text == text &&
          protocol.isOver == isOver
      case _ => false
    }
  }

  lazy val json: Option[JsonChatFromServer] = {
    Some(
      new JsonChatFromServer(
        BaseProtocol(
          Contexts.get(ChatMessage),
          ChatMessage,
          VillageProtocol(
            village.id,
            village.name,
            village.composition.totalNumberOfPlayers,
            village.language,
            ChatSettingsProtocol(
              village.id,
              village.maxNumberOfChatMessages,
              village.maxLengthOfUnicodeCodePoints
            )
          ),
          village.token,
          village.phase,
          village.day,
          village.phaseTimeLimit,
          village.phaseStartTime,
          Some(TimestampGenerator.now),
          Option.empty[OffsetDateTime],
          ServerToClient,
          channel.channel,
          List.empty[StatusCharacterProtocol],
          Option.empty[Seq[VotingResultSummaryProtocol]],
          Option.empty[Seq[VotingResultDetailProtocol]]
        ).json,
        character.json(LiCOSOnline.stateVillage(village.id)),
        isMine,
        id,
        counter,
        village.maxNumberOfChatMessages,
        interval,
        ChatTextProtocol(
          text,
          village.language
        ).json,
        village.maxLengthOfUnicodeCodePoints,
        isOver
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def fromHostPlayerToAvatar(token: UUID): ChatFromServerProtocol = {
    import cats.implicits._
    def isCharacterMine: Boolean = token === village.token
    ChatFromServerProtocol(
      village.changeToken(token): VillageInfo,
      channel:                    PlayerChatChannel,
      character:                  SimpleCharacterProtocol,
      isCharacterMine:            Boolean,
      id:                         Int,
      counter:                    Int,
      interval:                   Int,
      text:                       String,
      isOver:                     Boolean
    )
  }

  def forLogger(extensionalDisclosureRange: Seq[StatusCharacterProtocol]): server2logger.ChatFromServerProtocol = {
    server2logger.ChatFromServerProtocol(
      village:                    VillageInfo,
      channel:                    PlayerChatChannel,
      character:                  SimpleCharacterProtocol,
      isMine:                     Boolean,
      id:                         Int,
      counter:                    Int,
      interval:                   Int,
      text:                       String,
      isOver:                     Boolean,
      extensionalDisclosureRange: Seq[StatusCharacterProtocol]
    )
  }
}

object ChatFromServerProtocol {

  def read(json: JsonChatFromServer, villageInfoFromLobby: VillageInfoFromLobby): Option[ChatFromServerProtocol] = {
    VillageInfoFactory
      .createOpt(villageInfoFromLobby, json.base)
      .flatMap { village: VillageInfo =>
        for {
          channel   <- Data2Knowledge.playerChatChannelOpt(json.base.intensionalDisclosureRange)
          character <- Data2Knowledge.characterOpt(json.character.name.en, json.character.id)
        } yield {
          ChatFromServerProtocol(
            village,
            channel,
            SimpleCharacterProtocol(
              character,
              village.id,
              village.language
            ),
            json.isMine,
            json.id,
            json.counter,
            json.interval,
            json.text.`@value`,
            json.isOver
          )
        }
      }
  }

}
