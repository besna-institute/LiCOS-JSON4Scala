package licos.json.validation.village

import licos.util.LiCOSOnline
import play.api.libs.json.Reads
import play.api.libs.json.Reads.{max, min, pattern}
import play.api.libs.functional.syntax.*

import scala.util.matching.Regex

object VotingResultValidation {
  object votingResultsSummary {
    object item {
      val `@idRegex`: Regex = LiCOSOnline
        .stateRegex(
          s"""votingResultsSummary#${CharacterValidation.idStringPattern}"""
        )
        .r
      val `@id`:         Reads[String] = pattern(`@idRegex`)
      val numberOfVotes: Reads[Int]    = min(0) keepAnd max(15)
      val rankOfVotes:   Reads[Int]    = min(1) keepAnd max(15)
    }
  }

  object votingResultsDetails {
    object item {
      val `@idRegex`: Regex = LiCOSOnline
        .stateRegex(
          s"""votingResultsDetails#${CharacterValidation.idStringPattern}-${CharacterValidation.idStringPattern}"""
        )
        .r
      val `@id`: Reads[String] = pattern(`@idRegex`)
    }
  }
}
