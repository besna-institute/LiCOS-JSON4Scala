package licos.protocol.engine.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.GameResultProtocol4Logger
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait GameResultAnalysisEngine4Logger extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, gameResult: GameResultProtocol4Logger): Try[VillageMessageProtocol]
}

object GameResultAnalysisEngine4Logger {
  val name: String = "village.server2client.server2logger.GameResult"
}
