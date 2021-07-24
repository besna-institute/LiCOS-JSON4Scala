package licos.protocol.element.village.part

import java.util.Locale

import licos.json.element.village.JsonBoardResult
import licos.json.element.village.iri.BoardResultContext
import licos.knowledge.{Character, Phase, PolarityMark}
import licos.protocol.element.village.part.character.SimpleCharacterProtocol

final case class BoardResultProtocol(
    character: Character,
    polarity:  PolarityMark,
    phase:     Phase,
    day:       Int,
    villageId: Long,
    language:  Locale
) {

  override def hashCode(): Int = 533011

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: BoardResultProtocol =>
        protocol.character == character &&
          protocol.polarity == polarity &&
          protocol.phase == phase &&
          protocol.day == day &&
          protocol.villageId == villageId &&
          protocol.language == language
      case _ => false
    }
  }

  def json(`@id`: String): JsonBoardResult = {
    JsonBoardResult(
      BoardResultContext.iri,
      `@id`.concat(s"/board#${character.getId.toString}"),
      SimpleCharacterProtocol(character, villageId, language).json(`@id`),
      polarity.label,
      phase.label,
      day
    )
  }

}
