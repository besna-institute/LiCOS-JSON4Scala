package licos.protocol.element.lobby.part

import licos.json.element.lobby.JsonRoleSetting

final case class RoleSettingProtocol(
    villager:    Int,
    werewolf:    Int,
    seer:        Int,
    medium:      Int,
    madman:      Int,
    hunter:      Int,
    mason:       Int,
    werehamster: Int
) {

  override def hashCode(): Int = 522010

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: RoleSettingProtocol =>
        protocol.villager == villager &&
          protocol.werewolf == werewolf &&
          protocol.seer == seer &&
          protocol.medium == medium &&
          protocol.madman == madman &&
          protocol.hunter == hunter &&
          protocol.mason == mason &&
          protocol.werehamster == werehamster
      case _ => false
    }
  }

  lazy val json: Option[JsonRoleSetting] = {
    Some(
      JsonRoleSetting(
        villager,
        werewolf,
        seer,
        medium,
        madman,
        hunter,
        mason,
        werehamster
      )
    )
  }

}

object RoleSettingProtocol {
  def read(json: JsonRoleSetting): Option[RoleSettingProtocol] = {
    Some(
      RoleSettingProtocol(
        json.villager,
        json.werewolf,
        json.seer,
        json.medium,
        json.madman,
        json.hunter,
        json.mason,
        json.werehamster
      )
    )
  }
}
