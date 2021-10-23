package licos.protocol.engine.analysis.village.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.ErrorFromClientProtocol4Logger
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait ErrorFromClientAnalysisEngine4Logger extends VillageMessageAnalysisEngine {
  def process(box: VillageBOX, errorFromClient: ErrorFromClientProtocol4Logger): Try[VillageMessageProtocol]
}

object ErrorFromClientAnalysisEngine4Logger {
  val name: String = "village.client2server.server2logger.ErrorFromClient"
}
