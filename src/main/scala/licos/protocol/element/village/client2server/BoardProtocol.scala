package licos.protocol.element.village.client2server

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonBoard
import licos.knowledge.{Character, Data2Knowledge, PolarityMark, Role}
import licos.protocol.element.village.client2server.server2logger.BoardProtocol4Logger
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import play.api.libs.json.{JsValue, Json}

final case class BoardProtocol(
    village:     VillageInfo,
    character:   Character,
    role:        Role,
    prediction:  PolarityMark,
    myCharacter: Character,
    myRole:      Role
) extends Client2ServerVillageMessageProtocol {

  private lazy val json: Option[JsonBoard] = {
    BoardProtocol4Logger(village, character, role, prediction, myCharacter, myRole, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def forLogger(extensionalDisclosureRange: Seq[StatusCharacterProtocol]): BoardProtocol4Logger = {
    BoardProtocol4Logger(
      village:                    VillageInfo,
      character:                  Character,
      role:                       Role,
      prediction:                 PolarityMark,
      myCharacter:                Character,
      myRole:                     Role,
      extensionalDisclosureRange: Seq[StatusCharacterProtocol]
    )
  }

}

object BoardProtocol {

  def read(json: JsonBoard, villageInfoFromLobby: VillageInfoFromLobby): Option[BoardProtocol] = {
    VillageInfoFactory
      .createOpt(villageInfoFromLobby, json.base)
      .flatMap { village: VillageInfo =>
        for {
          prediction <- Data2Knowledge.polarityMarkOpt(json.prediction)
          character  <- Data2Knowledge.characterOpt(json.character.name.en, json.character.id)
          role <- Data2Knowledge
            .roleOpt(
              json.role.name.en,
              village.composition.parse(json.role.name.en).map(_.numberOfPlayers).getOrElse(0)
            )
          myCharacter <- Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
          myRole      <- village.composition.parse(json.myCharacter.role.name.en)
        } yield {
          BoardProtocol(
            village,
            character,
            role,
            prediction,
            myCharacter,
            myRole
          )
        }
      }
  }

}
