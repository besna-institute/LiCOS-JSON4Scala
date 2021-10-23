package licos.protocol.engine.analysis.village.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.StarProtocol4Logger
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait StarAnalysisEngine4Logger extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, star: StarProtocol4Logger): Try[VillageMessageProtocol]
}

object StarAnalysisEngine4Logger {
  val name: String = "village.client2server.server2logger.Star"
}
