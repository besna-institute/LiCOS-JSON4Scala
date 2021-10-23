package licos.protocol.element.village.client2server

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonScroll
import licos.knowledge.{Character, Data2Knowledge, Role}
import licos.protocol.element.village.client2server.server2logger.ScrollProtocol4Logger
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

  private lazy val json: Option[JsonScroll] = {
    ScrollProtocol4Logger(village, nodeId, scrollTop, scrollHeight, offsetHeight, myCharacter, myRole, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def forLogger(extensionalDisclosureRange: Seq[StatusCharacterProtocol]): ScrollProtocol4Logger = {
    ScrollProtocol4Logger(
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
