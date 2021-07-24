package licos.protocol.element.village.client2server

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonScroll
import licos.knowledge.{Character, Data2Knowledge, Role}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import play.api.libs.json.{JsValue, Json}

final case class ScrollProtocol(
    village:      VillageInfo,
    nodeId:       String,
    scrollTop:    Int,
    scrollHeight: Int,
    offsetHeight: Int,
    myCharacter:  Character,
    myRole:       Role
) extends Client2ServerVillageMessageProtocol {

  override def hashCode(): Int = 532014

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: ScrollProtocol =>
        protocol.village == village &&
          protocol.nodeId == nodeId &&
          protocol.scrollTop == scrollTop &&
          protocol.scrollHeight == scrollHeight &&
          protocol.offsetHeight == offsetHeight &&
          protocol.myCharacter == myCharacter &&
          protocol.myRole == myRole
      case _ => false
    }
  }

  private lazy val json: Option[JsonScroll] = {
    server2logger.ScrollProtocol(village, nodeId, scrollTop, scrollHeight, offsetHeight, myCharacter, myRole, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def forLogger(extensionalDisclosureRange: Seq[StatusCharacterProtocol]): server2logger.ScrollProtocol = {
    server2logger.ScrollProtocol(
      village:                    VillageInfo,
      nodeId:                     String,
      scrollTop:                  Int,
      scrollHeight:               Int,
      offsetHeight:               Int,
      myCharacter:                Character,
      myRole:                     Role,
      extensionalDisclosureRange: Seq[StatusCharacterProtocol]
    )
  }
}

object ScrollProtocol {

  def read(json: JsonScroll, villageInfoFromLobby: VillageInfoFromLobby): Option[ScrollProtocol] = {
    VillageInfoFactory
      .createOpt(villageInfoFromLobby, json.base)
      .flatMap { village: VillageInfo =>
        for {
          myCharacter <- Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
          myRole      <- village.composition.parse(json.myCharacter.role.name.en)
        } yield {
          ScrollProtocol(
            village,
            json.nodeId,
            json.scrollTop,
            json.scrollHeight,
            json.offsetHeight,
            myCharacter,
            myRole
          )
        }
      }
  }

}
