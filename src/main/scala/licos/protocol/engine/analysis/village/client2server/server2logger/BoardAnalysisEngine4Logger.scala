package licos.protocol.engine.analysis.village.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.BoardProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait BoardAnalysisEngine4Logger extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, board: BoardProtocol): Try[VillageMessageProtocol]
}

object BoardAnalysisEngine4Logger {
  val name: String = "village.client2server.server2logger.BoardAnalysisEngine"
}
