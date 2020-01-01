package licos.protocol.element.village.server2client

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.JsonError
import licos.json.element.village.iri.{Contexts, ErrorMessage}
import licos.knowledge.{Data2Knowledge, PrivateChannel, ServerToClient, Severity}
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, NameProtocol, VillageProtocol}
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

final case class ErrorFromServerProtocol(
    village:  VillageInfo,
    content:  NameProtocol,
    severity: Severity,
    source:   String
) extends Server2ClientVillageMessageProtocol {

  private val json: Option[JsonError] = {
    Some(
      new JsonError(
        BaseProtocol(
          Contexts.get(ErrorMessage),
          ErrorMessage,
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
          village.token,
          village.phase,
          village.day,
          village.phaseTimeLimit,
          village.phaseStartTime,
          Some(TimestampGenerator.now),
          None,
          ServerToClient,
          PrivateChannel,
          Nil,
          None,
          None
        ).json,
        content.json(Option(village.language)),
        severity.label,
        source,
        isFromServer = true
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object ErrorFromServerProtocol {

  def read(json: JsonError, villageInfoFromLobby: VillageInfoFromLobby): Option[ErrorFromServerProtocol] = {
    if (json.isFromServer) {
      VillageInfoFactory
        .create(villageInfoFromLobby, json.base)
        .flatMap { village: VillageInfo =>
          Data2Knowledge.severityOpt(json.severity).map { severity: Severity =>
            ErrorFromServerProtocol(
              village,
              Data2Knowledge.name(json.content),
              severity,
              json.source
            )
          }
        }
    } else {
      None
    }
  }

}
