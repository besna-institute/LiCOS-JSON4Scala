package licos.protocol.element.lobby.part

import java.net.URL
import java.util.{Locale, UUID}

import licos.json.element.lobby.server2client.JsonRobotPlayerInfo
import licos.knowledge.{Data2Knowledge, RobotPlayerStatus}
import licos.protocol.element.lobby.server2client.AvatarInfoProtocol

final case class RobotPlayerInfoProtocol(
    avatar:           AvatarInfoProtocol,
    status:           RobotPlayerStatus,
    isAuthorized:     Boolean,
    isTestPassed:     Boolean,
    isFullyAutomated: Boolean
) {

  override def hashCode(): Int = 522008

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: RobotPlayerInfoProtocol =>
        protocol.avatar == avatar &&
          protocol.status == status &&
          protocol.isAuthorized == isAuthorized &&
          protocol.isTestPassed == isTestPassed &&
          protocol.isFullyAutomated == isFullyAutomated
      case _ => false
    }
  }

  lazy val json: Option[JsonRobotPlayerInfo] = {
    Some(
      new JsonRobotPlayerInfo(
        avatar.token.toString,
        avatar.name,
        avatar.image.toString,
        avatar.language.getLanguage,
        status.label,
        isAuthorized,
        isTestPassed,
        isFullyAutomated
      )
    )
  }
}

object RobotPlayerInfoProtocol {
  def read(json: JsonRobotPlayerInfo): Option[RobotPlayerInfoProtocol] = {
    Data2Knowledge.robotPlayerStatusOpt(json.status).map { status: RobotPlayerStatus =>
      RobotPlayerInfoProtocol(
        AvatarInfoProtocol(
          UUID.fromString(json.token),
          json.name,
          new URL(json.image),
          new Locale(json.language)
        ),
        status,
        json.isAuthorized,
        json.isTestPassed,
        json.isFullyAutomated
      )
    }
  }
}
