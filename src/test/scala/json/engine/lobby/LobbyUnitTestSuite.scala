package json.engine.lobby

import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import json.engine.LobbyUnitTestExample
import json.engine.lobby.unitTestExample.{
  HostPlayer,
  Human,
  PingResult,
  PlayerInWaitingPage,
  PlayerSetting,
  PlayerTokenInKickOutPlayer,
  Robot,
  RobotPlayerInfo,
  RoleSetting,
  SubAvatarInfo,
  Support,
  SupportedComposition
}
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import json.parser.LobbyUnitTestParser
import play.api.libs.json.{JsValue, Json}

import scala.io.{Codec, Source}

object LobbyUnitTestSuite {

  @DataPoints
  def exampleSeq: Array[LobbyUnitTestExample] = Array[LobbyUnitTestExample](
    HostPlayer("hostPlayer.json"),
    Human("human.json"),
    PingResult("pingResult.json"),
    PlayerTokenInKickOutPlayer("playerInKickOutPlayer.json"),
    PlayerInWaitingPage("playerInWaitingPage.json"),
    Robot("robot.json"),
    RoleSetting("roleSetting.json"),
    PlayerSetting("playerSetting.json"),
    SupportedComposition("supportedComposition.json"),
    Support("support.json"),
    SubAvatarInfo("subAvatarInfo.json"),
    RobotPlayerInfo("robotPlayerInfo.json")
  )
}

@RunWith(classOf[Theories])
final class LobbyUnitTestSuite extends AssertionsForJUnit with LobbyUnitTestParser {

  private val log: Logger = Logger[LobbyUnitTestSuite]

  @Theory
  def process(jsonExample: LobbyUnitTestExample): Unit = {
    val jsonType:       String = jsonExample.`type`
    val url:            String = jsonExample.path
    implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
    log.info(url)
    val source = Source.fromURL(url)
    val msg: String = source.getLines.mkString("\n")
    source.close()
    log.debug(msg)
    val json: JsValue = Json.parse(msg)

    jsonType match {
      case "unitTest/HostPlayer" =>
        assert(parseHostPlayer(json).nonEmpty)
      case "unitTest/Human" =>
        assert(parseHuman(json).nonEmpty)
      case "unitTest/PingResult" =>
        assert(parsePingResult(json).nonEmpty)
      case "unitTest/PlayerInKickOutPlayer" =>
        assert(parsePlayerTokenInKickOutPlayer(json).nonEmpty)
      case "unitTest/PlayerInWaitingPage" =>
        assert(parsePlayerInWaitingPage(json).nonEmpty)
      case "unitTest/PlayerSetting" =>
        assert(parsePlayerSetting(json).nonEmpty)
      case "unitTest/Robot" =>
        assert(parseRobot(json).nonEmpty)
      case "unitTest/RoleSetting" =>
        assert(parseRoleSetting(json).nonEmpty)
      case "unitTest/SupportedComposition" =>
        assert(parseSupportedComposition(json).nonEmpty)
      case "unitTest/Support" =>
        assert(parseSupport(json).nonEmpty)
      case "unitTest/SubAvatarInfo" =>
        assert(parseSubAvatarInfo(json).nonEmpty)
      case "unitTest/RobotPlayerInfo" =>
        assert(parseRobotPlayerInfo(json).nonEmpty)
      case _ =>
        fail("no json type")
    }
  }
}
