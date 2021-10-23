package licos.json2protocol.village.server2logger

import licos.entity.VillageInfoFromLobby
import licos.json.element.village.{JsonAnonymousAudienceChat, JsonError, JsonOnymousAudienceChat}
import licos.json.element.village.client2server.{
  JsonBoard,
  JsonChatFromClient,
  JsonOnymousAudienceBoard,
  JsonOnymousAudienceScroll,
  JsonScroll,
  JsonStar,
  JsonVote
}
import licos.json.element.village.server2client.{JsonChatFromServer, JsonFlavorText, JsonGameResult, JsonPhase}
import licos.json.flow.{FlowController, VillageFlowController}
import licos.json2protocol.Json2Protocol
import licos.knowledge.{Morning, Night, Noon, PostMortemDiscussion}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.{
  AnonymousAudienceChatFromClientProtocol4Logger,
  BoardProtocol4Logger,
  ChatFromClientProtocol4Logger,
  ErrorFromClientProtocol4Logger,
  OnymousAudienceBoardProtocol4Logger,
  OnymousAudienceChatFromClientProtocol4Logger,
  OnymousAudienceScrollProtocol4Logger,
  ScrollProtocol4Logger,
  StarProtocol4Logger,
  VoteProtocol4Logger
}
import licos.protocol.element.village.server2client.server2logger.{
  AnonymousAudienceChatFromServerProtocol4Logger,
  ChatFromServerProtocol4Logger,
  ErrorFromServerProtocol4Logger,
  FirstMorningPhaseProtocol4Logger,
  FlavorTextProtocol4Logger,
  GameResultProtocol4Logger,
  MorningPhaseProtocol4Logger,
  NightPhaseProtocol4Logger,
  NoonPhaseProtocol4Logger,
  OnymousAudienceChatFromServerProtocol4Logger,
  PostMortemDiscussionProtocol4Logger
}
import play.api.libs.json.JsValue

object Json2VillageMessageProtocol extends Json2Protocol {
  override protected val flowController: FlowController = new VillageFlowController()

  def toProtocolOpt(json: JsValue, villageInfoFromLobby: VillageInfoFromLobby): Option[VillageMessageProtocol] = {
    flowController.flow(json) match {
      case Right(json: JsonAnonymousAudienceChat) =>
        if (json.isFromServer) {
          AnonymousAudienceChatFromServerProtocol4Logger.read(json, villageInfoFromLobby)
        } else {
          AnonymousAudienceChatFromClientProtocol4Logger.read(json, villageInfoFromLobby)
        }
      case Right(json: JsonBoard) =>
        BoardProtocol4Logger.read(json, villageInfoFromLobby)
      case Right(json: JsonChatFromClient) =>
        ChatFromClientProtocol4Logger.read(json, villageInfoFromLobby)
      case Right(json: JsonError) =>
        if (json.isFromServer) {
          ErrorFromServerProtocol4Logger.read(json, villageInfoFromLobby)
        } else {
          ErrorFromClientProtocol4Logger.read(json, villageInfoFromLobby)
        }
      case Right(json: JsonOnymousAudienceBoard) =>
        OnymousAudienceBoardProtocol4Logger.read(json, villageInfoFromLobby)
      case Right(json: JsonOnymousAudienceChat) =>
        if (json.isFromServer) {
          OnymousAudienceChatFromServerProtocol4Logger.read(json, villageInfoFromLobby)
        } else {
          OnymousAudienceChatFromClientProtocol4Logger.read(json, villageInfoFromLobby)
        }
      case Right(json: JsonOnymousAudienceScroll) =>
        OnymousAudienceScrollProtocol4Logger.read(json, villageInfoFromLobby)
      case Right(json: JsonScroll) =>
        ScrollProtocol4Logger.read(json, villageInfoFromLobby)
      case Right(json: JsonStar) =>
        StarProtocol4Logger.read(json, villageInfoFromLobby)
      case Right(json: JsonVote) =>
        VoteProtocol4Logger.read(json, villageInfoFromLobby)
      case Right(json: JsonChatFromServer) =>
        ChatFromServerProtocol4Logger.read(json, villageInfoFromLobby)
      case Right(json: JsonPhase) =>
        json.base.phase match {
          case Morning.label =>
            import cats.implicits.*
            if (json.base.day === 1) {
              FirstMorningPhaseProtocol4Logger.read(json, villageInfoFromLobby)
            } else {
              MorningPhaseProtocol4Logger.read(json, villageInfoFromLobby)
            }
          case Noon.label =>
            NoonPhaseProtocol4Logger.read(json, villageInfoFromLobby)
          case Night.label =>
            NightPhaseProtocol4Logger.read(json, villageInfoFromLobby)
          case PostMortemDiscussion.label =>
            PostMortemDiscussionProtocol4Logger.read(json, villageInfoFromLobby)
          case _ =>
            Option.empty[VillageMessageProtocol]
        }
      case Right(json: JsonFlavorText) =>
        FlavorTextProtocol4Logger.read(json, villageInfoFromLobby)
      case Right(json: JsonGameResult) =>
        GameResultProtocol4Logger.read(json, villageInfoFromLobby)
      case _ =>
        Option.empty[VillageMessageProtocol]
    }
  }
}
