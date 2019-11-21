package licos.protocol.engine.analysis.village.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.StarProtocol
import licos.protocol.engine.analysis.village.VillageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait StarAnalysisEngine extends VillageAnalysisEngine {
  def process(box: VillageBOX, star: StarProtocol): Try[VillageMessageProtocol]
}

object StarAnalysisEngine {
  val name: String = "village.client2server.server2logger.Star"
}