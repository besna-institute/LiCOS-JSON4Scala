package licos.protocol.element.village.part

import java.util.Locale

import licos.json.element.village.JsonVotingResultSummary
import licos.knowledge.Character
import licos.protocol.element.village.part.character.SimpleCharacterProtocol
import licos.util.LiCOSOnline

final case class VotingResultSummaryProtocol(
    character:     Character,
    numberOfVotes: Int,
    rankOfVotes:   Int,
    villageId:     Long,
    language:      Locale
) {

  override def hashCode(): Int = 533019

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: VotingResultSummaryProtocol =>
        protocol.character == character &&
          protocol.numberOfVotes == numberOfVotes &&
          protocol.rankOfVotes == rankOfVotes &&
          protocol.villageId == villageId &&
          protocol.language == language
      case _ => false
    }
  }

  lazy val json: JsonVotingResultSummary = {
    val `@id`: String = LiCOSOnline.state(villageId, s"votingResultsSummary#${character.getId.toString}")
    JsonVotingResultSummary(
      `@id`,
      SimpleCharacterProtocol(
        character,
        villageId,
        language
      ).json(`@id`),
      numberOfVotes,
      rankOfVotes
    )
  }

}
