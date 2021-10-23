package licos.protocol.engine.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.FlavorTextProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait FlavorTextAnalysisEngine4Logger extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, flavorText: FlavorTextProtocol): Try[VillageMessageProtocol]
}

object FlavorTextAnalysisEngine4Logger {
  val name: String = "village.server2client.server2logger.FlavorText"
}
