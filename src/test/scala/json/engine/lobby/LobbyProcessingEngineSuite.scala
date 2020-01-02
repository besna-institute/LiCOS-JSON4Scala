package json.engine.lobby

import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import json.engine.LobbyExample
import json.element.JsonTest
import json.engine.lobby.analysis.client2server.{
  AdvancedSearchAE,
  AuthorizationRequestAcceptedAE,
  BuildVillageAE,
  ChangeLangAE,
  ChangeUserEmailAE,
  ChangeUserNameAE,
  ChangeUserPasswordAE,
  EnterLobbyAE,
  GetAvatarInfoAE,
  GetSettingsAE,
  IdSearchAE,
  KickOutPlayerAE,
  LeaveWaitingPageAE,
  PlayAE,
  PongAE,
  ReadyAE,
  RenewAvatarTokenAE,
  SelectVillageAE
}
import json.engine.lobby.analysis.server2client.{
  AuthorizationRequestAE,
  AuthorizationRequestAcceptedResponseAE,
  AvatarInfoAE,
  LobbyAE,
  NewAvatarTokenAE,
  PingAE,
  PlayedAE,
  SearchResultAE,
  SettingsAE,
  WaitingPageAE
}
import json.engine.lobby.analysis.server2server.PlayedWithTokenAE
import json.engine.lobby.example.client2server.{
  AdvancedSearch,
  AuthorizationRequestAccepted,
  BuildVillage,
  ChangeLang,
  ChangeUserEmail,
  ChangeUserName,
  ChangeUserPassword,
  EnterLobby,
  GetAvatarInfo,
  GetSettings,
  IdSearch,
  KickOutPlayer,
  LeaveWaitingPage,
  Play,
  Pong,
  Ready,
  RenewAvatarToken,
  SelectVillage
}
import json.engine.lobby.example.server2client.{
  AuthorizationRequest,
  AuthorizationRequestAcceptedResponse,
  AvatarInfo,
  Lobby,
  NewAvatarToken,
  Ping,
  Played,
  SearchResult,
  Settings,
  WaitingPage
}
import json.engine.lobby.example.server2server.PlayedWithToken
import licos.json.engine.processing.{
  LobbyPE,
  LobbyProcessingEngine,
  LobbyProcessingEngineFactory,
  SpecificProcessingEngineFactory
}
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import play.api.libs.json.{JsResult, JsValue}

import scala.io.{Codec, Source}
import scala.util.{Failure, Success, Try}

object LobbyProcessingEngineSuite {
  @DataPoints
  def jsonExampleSeq: Array[LobbyExample] = Array[LobbyExample](
    AdvancedSearch("advancedSearch.json"),
    BuildVillage("buildVillage.json"),
    ChangeLang("changeLang.json"),
    ChangeUserEmail("changeUserEmail.json"),
    ChangeUserName("changeUserName.json"),
    ChangeUserPassword("changeUserPassword.json"),
    EnterLobby("enterLobbyForAnonymousAudience.json"),
    EnterLobby("enterLobbyForHumanPlayer.json"),
    EnterLobby("enterLobbyForOnymousAudience.json"),
    GetAvatarInfo("getAvatar.json"),
    GetSettings("getSettings.json"),
    IdSearch("idSearch.json"),
    KickOutPlayer("kickOutPlayer.json"),
    LeaveWaitingPage("leaveWaitingPage.json"),
    Play("play.json"),
    Pong("pong.json"),
    Ready("ready.json"),
    SelectVillage("selectVillageForHumanPlayer.json"),
    AvatarInfo("avatar.json"),
    Ping("ping.json"),
    Settings("settings.json"),
    Lobby("lobbyForHumanPlayer.json"),
    Played("played.json"),
    PlayedWithToken("playedWithToken.json"),
    SearchResult("searchResult.json"),
    WaitingPage("waitingPageForHumanPlayer.json"),
    AuthorizationRequestAccepted("authorizationRequestAccepted.json"),
    AuthorizationRequest("authorizationRequest.json"),
    AuthorizationRequestAcceptedResponse("authorizationRequestAcceptedResponse.json"),
    RenewAvatarToken("renewAvatarToken.json"),
    NewAvatarToken("newAvatarToken.json")
  )
}

@RunWith(classOf[Theories])
final class LobbyProcessingEngineSuite extends AssertionsForJUnit {

  private val log: Logger = Logger[LobbyProcessingEngineSuite]

  private val processingEngineFactory: LobbyProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(LobbyPE)
    .asInstanceOf[LobbyProcessingEngineFactory]
    .set(new PongAE())
    .set(new PingAE())
    .set(new WaitingPageAE())
    .set(new LobbyAE())
    .set(new EnterLobbyAE())
    .set(new GetAvatarInfoAE())
    .set(new AvatarInfoAE())
    .set(new SelectVillageAE())
    .set(new LeaveWaitingPageAE())
    .set(new KickOutPlayerAE())
    .set(new BuildVillageAE())
    .set(new AdvancedSearchAE())
    .set(new IdSearchAE())
    .set(new PlayAE())
    .set(new PlayedAE())
    .set(new PlayedWithTokenAE())
    .set(new ReadyAE())
    .set(new SearchResultAE())
    .set(new ChangeLangAE())
    .set(new ChangeUserEmailAE())
    .set(new ChangeUserNameAE())
    .set(new ChangeUserPasswordAE())
    .set(new GetSettingsAE())
    .set(new SettingsAE())
    .set(new AuthorizationRequestAcceptedAE())
    .set(new AuthorizationRequestAE())
    .set(new AuthorizationRequestAcceptedResponseAE())
    .set(new RenewAvatarTokenAE())
    .set(new NewAvatarTokenAE())

  private val processingEngine: LobbyProcessingEngine = processingEngineFactory.create

  @Theory
  def process(jsonExample: LobbyExample): Unit = {
    val jsonType:       String = jsonExample.`type`
    val url:            String = jsonExample.path
    implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
    log.info(url)
    val source = Source.fromURL(url)
    val msg: String = source.getLines.mkString("\n")
    source.close()
    log.debug(msg)
    processingEngine.process(new LobbyBox(jsonType), msg) match {
      case Right(jsValue: JsValue) =>
        parseJsonTest(jsValue) match {
          case Some(json: JsonTest) =>
            assert(json.text == jsonType)
          case None =>
            fail(
              Seq[String](
                "Something is wrong right after parsing.",
                msg
              ).mkString("\n")
            )
        }

      case Left(jsValue: JsValue) =>
        fail(
          Seq[String](
            "No response is generated.",
            msg,
            jsValue.toString
          ).mkString("\n")
        )
    }
  }

  private def parseJsonTest(jsValue: JsValue): Option[JsonTest] = {
    Try(jsValue.validate[JsonTest]) match {
      case Success(json: JsResult[JsonTest]) => json.asOpt
      case Failure(err:  Throwable) =>
        fail(
          Seq[String](
            "Parsing failed.",
            err.getMessage,
            jsValue.toString
          ).mkString("\n")
        )
        None
    }
  }
}
