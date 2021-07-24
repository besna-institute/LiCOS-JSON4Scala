package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonBuildVillage
import licos.knowledge.{AvatarSetting, Data2Knowledge}
import licos.protocol.element.lobby.part.{HostPlayerProtocol, PlayerSettingProtocol, RoleSettingProtocol}
import play.api.libs.json.{JsValue, Json}

final case class BuildVillageProtocol(
    token:          UUID,
    name:           String,
    id:             Long,
    idForSearching: Long,
    hostPlayer:     HostPlayerProtocol,
    playerSetting:  PlayerSettingProtocol,
    roleSetting:    RoleSettingProtocol,
    avatar:         AvatarSetting,
    comment:        Option[String]
) extends Client2ServerLobbyMessageProtocol {

  override def hashCode(): Int = 521003

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: BuildVillageProtocol =>
        protocol.token == token &&
          protocol.name == name &&
          protocol.id == id &&
          protocol.idForSearching == idForSearching &&
          protocol.hostPlayer == hostPlayer &&
          protocol.playerSetting == playerSetting &&
          protocol.roleSetting == roleSetting &&
          protocol.avatar == avatar &&
          protocol.comment == comment
      case _ => false
    }
  }

  private lazy val json: Option[JsonBuildVillage] = {
    for {
      jsonHostPlayer    <- hostPlayer.json
      jsonPlayerSetting <- playerSetting.json
      jsonRoleSetting   <- roleSetting.json
    } yield {
      new JsonBuildVillage(
        token.toString,
        name,
        id,
        idForSearching,
        jsonHostPlayer,
        jsonPlayerSetting,
        jsonRoleSetting,
        avatar.label,
        comment
      )
    }
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object BuildVillageProtocol {

  def read(json: JsonBuildVillage): Option[BuildVillageProtocol] = {
    for {
      hostPlayer    <- HostPlayerProtocol.read(json.hostPlayer)
      playerSetting <- PlayerSettingProtocol.read(json.playerSetting)
      roleSetting   <- RoleSettingProtocol.read(json.roleSetting)
      avatar        <- Data2Knowledge.avatarSettingOpt(json.avatar)
    } yield {
      BuildVillageProtocol(
        UUID.fromString(json.token),
        json.name,
        json.id,
        json.idForSearching,
        hostPlayer,
        playerSetting,
        roleSetting,
        avatar,
        json.comment
      )
    }
  }

}
