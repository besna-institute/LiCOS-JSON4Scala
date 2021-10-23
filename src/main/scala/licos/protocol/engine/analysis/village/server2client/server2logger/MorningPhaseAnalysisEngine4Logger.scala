package licos.protocol.engine.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.MorningPhaseProtocol4Logger
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait MorningPhaseAnalysisEngine4Logger extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, morningPhase: MorningPhaseProtocol4Logger): Try[VillageMessageProtocol]
}

object MorningPhaseAnalysisEngine4Logger {
  val name: String = "village.server2client.server2logger.MorningPhase"
}
