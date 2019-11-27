package licos.protocol.element.village.client2server.server2logger

import licos.entity.Village
import licos.json.element.village.JsonAnonymousAudienceChat
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{ChatMessage, Contexts}
import licos.knowledge.{AnonymousAudienceChannel, Character, ClientToServer, Data2Knowledge, Role, Status}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

@SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
final case class AnonymousAudienceChatFromClientProtocol(
    village:                    Village,
    text:                       String,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

  val json: Option[JsonAnonymousAudienceChat] = {
    if (village.isAvailable) {
      Some(
        new JsonAnonymousAudienceChat(
          BaseProtocol(
            Contexts.get(ChatMessage),
            ChatMessage,
            VillageProtocol(
              village.id,
              village.name,
              village.cast.totalNumberOfPlayers,
              village.language,
              ChatSettingsProtocol(
                village.id,
                village.maxNumberOfChatMessages,
                village.maxLengthOfUnicodeCodePoints
              )
            ),
            village.tokenOpt.get,
            village.currentPhase,
            village.currentDay,
            village.currentPhase.timeLimit(village.currentDay, village.numberOfAlivePlayers).get,
            village.phaseStartTimeOpt.get,
            None,
            Option(TimestampGenerator.now),
            ClientToServer,
            AnonymousAudienceChannel,
            extensionalDisclosureRange,
            None,
            None
          ).json,
          isMine = true,
          ChatTextProtocol(text, village.language).json,
          village.maxLengthOfUnicodeCodePoints,
          isFromServer = false
        )
      )
    } else {
      None
    }
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonAnonymousAudienceChat =>
      Json.toJson(j)
    }
  }

}

object AnonymousAudienceChatFromClientProtocol {

  @SuppressWarnings(
    Array[String](
      "org.wartremover.warts.Any",
      "org.wartremover.warts.MutableDataStructures",
      "org.wartremover.warts.OptionPartial"
    )
  )
  def read(json: JsonAnonymousAudienceChat, village: Village): Option[AnonymousAudienceChatFromClientProtocol] = {
    if (!json.isFromServer) {

      val statusCharacterBuffer = ListBuffer.empty[StatusCharacterProtocol]
      json.base.extensionalDisclosureRange foreach { jsonStatusCharacter: JsonStatusCharacter =>
        val characterOpt: Option[Character] =
          Data2Knowledge.characterOpt(jsonStatusCharacter.name.en, jsonStatusCharacter.id)
        val roleOpt:   Option[Role]   = village.cast.parse(jsonStatusCharacter.role.name.en)
        val statusOpt: Option[Status] = Data2Knowledge.statusOpt(jsonStatusCharacter.status)
        if (characterOpt.nonEmpty && roleOpt.nonEmpty && statusOpt.nonEmpty) {
          statusCharacterBuffer += StatusCharacterProtocol(
            characterOpt.get,
            roleOpt.get,
            statusOpt.get,
            jsonStatusCharacter.isHumanPlayer,
            village.id,
            village.language
          )
        }
      }

      Some(
        AnonymousAudienceChatFromClientProtocol(
          village,
          json.text.`@value`,
          statusCharacterBuffer.result
        )
      )
    } else {
      None
    }
  }

}
