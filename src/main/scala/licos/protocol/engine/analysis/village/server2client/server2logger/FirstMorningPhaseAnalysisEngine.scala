package licos.protocol.engine.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.FirstMorningPhaseProtocol
import licos.protocol.engine.analysis.village.VillageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait FirstMorningPhaseAnalysisEngine extends VillageAnalysisEngine {
  def process(box: VillageBOX, firstMorningPhase: FirstMorningPhaseProtocol): Try[VillageMessageProtocol]
}

object FirstMorningPhaseAnalysisEngine {
  val name: String = "village.server2client.server2logger.FirstMorningPhase"
}
