package licos.protocol.engine.analysis.village.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.AnonymousAudienceChatFromClientProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait AnonymousAudienceChatFromClientAnalysisEngine4Logger extends VillageMessageAnalysisEngine {
  def process(
      box:                             VillageBOX,
      anonymousAudienceChatFromClient: AnonymousAudienceChatFromClientProtocol
  ): Try[VillageMessageProtocol]
}

object AnonymousAudienceChatFromClientAnalysisEngine4Logger {
  val name: String = "village.client2server.server2logger.AnonymousAudienceChatFromClient"
}
