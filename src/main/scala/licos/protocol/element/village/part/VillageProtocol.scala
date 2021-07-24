package licos.protocol.element.village.part

import java.util.Locale

import licos.json.element.village.JsonVillage
import licos.json.element.village.iri.VillageContext
import licos.util.LiCOSOnline

final case class VillageProtocol(
    id:                   Long,
    name:                 String,
    totalNumberOfPlayers: Int,
    language:             Locale,
    chatSettings:         ChatSettingsProtocol
) {

  override def hashCode(): Int = 533017

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: VillageProtocol =>
        protocol.id == id &&
          protocol.name == name &&
          protocol.totalNumberOfPlayers == totalNumberOfPlayers &&
          protocol.language == language &&
          protocol.chatSettings == chatSettings
      case _ => false
    }
  }

  lazy val json: JsonVillage = JsonVillage(
    VillageContext.iri,
    LiCOSOnline.stateVillage,
    id,
    name,
    totalNumberOfPlayers,
    language.getLanguage,
    chatSettings.json
  )

}
