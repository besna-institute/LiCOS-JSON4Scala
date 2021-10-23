package licos.protocol.element.village.server2client.server2logger

import java.time.OffsetDateTime

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{Contexts, FlavorTextMessage}
import licos.json.element.village.server2client.{JsonChatFromServer, JsonFlavorText}
import licos.knowledge.{Data2Knowledge, PublicChannel, ServerToClient}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{
  BaseProtocol,
  ChatSettingsProtocol,
  VillageProtocol,
  VotingResultDetailProtocol,
  VotingResultSummaryProtocol
}
import licos.protocol.element.village.server2client.{
  ChatFromServerProtocol,
  FlavorTextProtocol as SimpleFlavorTextProtocol
}
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

final case class FlavorTextProtocol4Logger(
    village:                    VillageInfo,
    flavorText:                 Seq[ChatFromServerProtocol],
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Server2ClientVillageMessageProtocol4Logger {

  lazy val json: Option[JsonFlavorText] = {
    Some(
      new JsonFlavorText(
        BaseProtocol(
          Contexts.get(FlavorTextMessage),
          FlavorTextMessage,
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
          PublicChannel,
          extensionalDisclosureRange,
          Option.empty[Seq[VotingResultSummaryProtocol]],
          Option.empty[Seq[VotingResultDetailProtocol]]
        ).json,
        flavorText.flatMap(_.json.toList)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def simpleProtocol: SimpleFlavorTextProtocol = SimpleFlavorTextProtocol(
    village:    VillageInfo,
    flavorText: Seq[ChatFromServerProtocol]
  )

}

object FlavorTextProtocol4Logger {

  def read(json: JsonFlavorText, villageInfoFromLobby: VillageInfoFromLobby): Option[FlavorTextProtocol4Logger] = {
    VillageInfoFactory
      .createOpt(villageInfoFromLobby, json.base)
      .map { village: VillageInfo =>
        FlavorTextProtocol4Logger(
          village,
          json.flavorText.flatMap { jsonChatFromServer: JsonChatFromServer =>
            ChatFromServerProtocol
              .read(jsonChatFromServer, villageInfoFromLobby)
              .toList
          },
          json.base.extensionalDisclosureRange.flatMap { jsonStatusCharacter: JsonStatusCharacter =>
            for {
              character  <- Data2Knowledge.characterOpt(jsonStatusCharacter.name.en, jsonStatusCharacter.id).toList
              role       <- village.composition.parse(jsonStatusCharacter.role.name.en).toList
              status     <- Data2Knowledge.statusOpt(jsonStatusCharacter.status).toList
              playerType <- Data2Knowledge.architectureOpt(jsonStatusCharacter.playerType).toList
            } yield {
              StatusCharacterProtocol(
                character,
                role,
                status,
                playerType,
                village.id,
                village.language
              )
            }
          }
        )
      }
  }

}
